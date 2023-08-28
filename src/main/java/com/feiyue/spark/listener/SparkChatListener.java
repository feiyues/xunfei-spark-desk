package com.feiyue.spark.listener;

import com.feiyue.spark.client.SparkMessageText;
import com.feiyue.spark.client.SparkSyncChatRes;
import com.feiyue.spark.client.req.SparkReq;
import com.feiyue.spark.client.res.SparkRes;
import com.feiyue.spark.client.res.SparkResHeader;
import com.feiyue.spark.client.res.SparkResPayloadChoices;
import com.feiyue.spark.client.res.SparkResPayloadUsage;
import com.feiyue.spark.enums.SparkErrorEnum;
import com.feiyue.spark.exception.SparkException;
import com.feiyue.spark.util.SparkUtils;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.util.List;

/**
 * 星火大模型websocket会话
 *
 * @author feiyue
 */
public class SparkChatListener extends WebSocketListener {

    private SparkReq sparkReq;
    private final SparkSyncChatRes syncChatRes;
    private final StringBuilder stringBuilder = new StringBuilder();

    public SparkChatListener(SparkSyncChatRes syncChatRes) {
        this.syncChatRes = syncChatRes;
    }

    /**
     * 收到回答时会调用此方法
     *
     * @param content       回答内容
     * @param usage         tokens消耗统计
     * @param status        会话状态，取值为[0,1,2]；
     *                      0代表首次结果；1代表中间结果；
     *                      2代表最后一个结果，当status为2时，webSocket连接会自动关闭
     * @param req  本次会话的请求参数
     * @param res 本次回调的响应数据
     */
    public void onMessage(String content, SparkResPayloadUsage usage, Integer status, SparkReq req, SparkRes res) {
        // 重写此方法，实现业务逻辑
        stringBuilder.append(content);
        String uidMsg = "uid:[" + req.getHeader().getUid() + "], chatId:["+req.getParameter().getChat().getChatId()+"], ";
        // 0代表首次结果
        if (0 == status) {
            List<SparkMessageText> messages = req.getPayload().getMessage().getText();
            System.out.println(uidMsg + "开始提问：" + SparkUtils.toJson(messages));
            System.out.println(uidMsg + "开始收到回答：");
        }

        System.out.println(uidMsg + "--content：" + content
                + " --完整响应：" + SparkUtils.toJson(res));

        if (2 == status) {
            syncChatRes.setContent(stringBuilder.toString());
            syncChatRes.setUsage(usage.getText());
            syncChatRes.setComplete(true);
        }
    }

    @Override
    public final void onOpen(WebSocket webSocket, Response response) {
        // 发送消息
        String requestJson = SparkUtils.toJson(sparkReq);
        webSocket.send(requestJson);
    }

    @Override
    public final void onMessage(WebSocket webSocket, String text) {

        // 解析响应
        SparkRes res = SparkUtils.fromJson(text,SparkRes.class);

        SparkResHeader header = res.getHeader();
        if (null == header) {
            webSocket.close(1000, "响应数据header为null");
            throw new SparkException(500, "响应数据不完整 SparkRes.header为null，完整响应：" + text);
        }

        // 业务状态判断
        Integer code = header.getCode();
        if (0 != code) {
            webSocket.close(1000, "");
            String errorMsg = SparkErrorEnum.getMsgByCode(code);
            throw new SparkException(code, errorMsg);
        }

        //大模型回复消息文本
        SparkResPayloadChoices choices = res.getPayload().getChoices();
        List<SparkMessageText> messages = choices.getText();
        StringBuilder stringBuilder = new StringBuilder();
        for (SparkMessageText message : messages) {
            stringBuilder.append(message.getContent());
        }
        String content = stringBuilder.toString();

        SparkResPayloadUsage usage = res.getPayload().getUsage();
        Integer status = header.getStatus();

        this.onMessage(content, usage, status, sparkReq, res);

        //最后一条结果，关闭连接
        if (2 == status) {
            String uidMsg = "uid:[" + sparkReq.getHeader().getUid() + "], chatId:[" + sparkReq.getParameter().getChat().getChatId()+"], ";
            System.out.println(uidMsg + "最后一条结果，关闭webSocket连接");
            webSocket.close(1000, "");
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        throw new SparkException(500, "星火Api webSocket 连接异常：" + t.getMessage(), t);
    }

    public SparkReq getSparkReq() {
        return sparkReq;
    }

    public void setSparkReq(SparkReq req) {
        this.sparkReq = req;
    }
}
