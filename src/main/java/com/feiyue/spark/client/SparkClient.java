package com.feiyue.spark.client;

import com.feiyue.spark.client.req.SparkReq;
import com.feiyue.spark.exception.SparkException;
import com.feiyue.spark.listener.SparkChatListener;
import com.feiyue.spark.util.SparkUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Spark星火大模型通信客户端
 * @author feiyue
 */
public class SparkClient {

    public OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .build();

    public WebSocket chatStream(SparkReq req, SparkChatListener listener) {
        //为lister设置请求参数
        listener.setSparkReq(req);

        //构建鉴权url
        String authWssUrl = null;
        try {
            String domain = req.getParameter().getChat().getDomain();
            authWssUrl = SparkUtils.getAuthUrl(domain)
                    .replace("http://", "ws://")
                    .replace("https://", "wss://");
            System.out.println("domain:" + domain + ",\nauthWssUrl:" + authWssUrl);
        } catch (Exception e) {
            throw new SparkException(500, "构建鉴权url失败", e);
        }
        //创建请求
        Request request = new Request.Builder().url(authWssUrl).build();
        //发送请求
        return client.newWebSocket(request, listener);
    }

    /**
     * 同步会话
     * （采用轮询方式等待）
     * @param req
     * @return
     */
    public SparkSyncChatRes chatSync(SparkReq req) {
        SparkSyncChatRes chatRes = new SparkSyncChatRes();
        SparkChatListener syncChatListener = new SparkChatListener(chatRes);
        //开启新的对话
        this.chatStream(req, syncChatListener);
        AtomicInteger count = new AtomicInteger(0);
        while (!chatRes.isComplete()) {
            try {
                Thread.sleep(200);
                System.out.println("uid[" + req.getHeader().getUid()
                                 + "]轮询等待请求结果,当前次数:" + count.incrementAndGet());
                if(count.get() > 300){
                    // 60 * 1000 / 200 = 300
                    System.out.println("uid[" + req.getHeader().getUid()
                            + "]轮询等待超过60s,当前次数:" + count.get());
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("轮询等待异常");
            }
        }
        return chatRes;
    }



}
