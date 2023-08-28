package com.feiyue;

import com.feiyue.spark.client.SparkClient;
import com.feiyue.spark.client.SparkMessageText;
import com.feiyue.spark.client.SparkSyncChatRes;
import com.feiyue.spark.client.req.SparkReq;
import com.feiyue.spark.client.res.SparkResUsageText;
import com.feiyue.spark.util.SparkChatCallable;
import com.feiyue.spark.util.SparkUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author feiyue
 */
public class SparkDeskTest {

    private SparkClient sparkClient = new SparkClient();

    // 消息列表，可以在此列表添加历史对话记录
    List<SparkMessageText> messages = new ArrayList<>();

    {
        messages.add(SparkMessageText.userContent("我希望你充当知识专家，回答我的问题时简洁专业，不要有多余的解释。"));
        messages.add(SparkMessageText.assistantContent("好的，我会尽量简洁专业地回答你的问题。请问你有什么问题需要我解答？"));
    }

    @Test
    public void chatSyncV1Test() {
        messages.add(SparkMessageText.userContent("写一首描述秋天的诗歌，100字以内"));
        String uid = "U1001";
        String chatId = uid + "_" + SparkUtils.getRandom(4);

        SparkReq req = SparkUtils.getSparkReqV1(messages,uid,chatId);

        // 同步调用
        SparkSyncChatRes chatRes = sparkClient.chatSync(req);
        SparkResUsageText usage = chatRes.getUsage();

        System.out.println("\n完整回答：" + chatRes.getContent());
        System.out.println("提问消耗tokens：" + usage.getPromptTokens()
                + "，回答消耗tokens：" + usage.getCompletionTokens()
                + "，总消耗tokens：" + usage.getTotalTokens());
    }

    @Test
    public void chatSyncV2Test() {

        messages.add(SparkMessageText.userContent("写一首描述秋天的诗歌，100字以内"));
        String uid = "U1001";
        String chatId = uid + "_" + SparkUtils.getRandom(4);

        SparkReq req = SparkUtils.getSparkReqV2(messages,uid,chatId);

        // 同步调用
        SparkSyncChatRes chatRes = sparkClient.chatSync(req);
        SparkResUsageText usage = chatRes.getUsage();

        System.out.println("\n完整回答：" + chatRes.getContent());
        System.out.println("提问消耗tokens：" + usage.getPromptTokens()
                + "，回答消耗tokens：" + usage.getCompletionTokens()
                + "，总消耗tokens：" + usage.getTotalTokens());
    }


    @Test
    public void chatCallableTest() {
        try {
            messages.add(SparkMessageText.userContent("写一首描述秋天的诗歌，100字以内"));
            String uid = "U1002";
            String chatId = uid + "_" + SparkUtils.getRandom(4);

            SparkReq req = SparkUtils.getSparkReqV2(messages,uid,chatId);

            SparkChatCallable callable = new SparkChatCallable(req,sparkClient);

            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<SparkSyncChatRes> future = executorService.submit(callable);

            //获取线程的返回值
            SparkSyncChatRes chatRes = future.get();
            SparkResUsageText usage = chatRes.getUsage();

            System.out.println("\n回答：" + chatRes.getContent());
            System.out.println("提问消耗tokens：" + usage.getPromptTokens()
                    + "，回答消耗tokens：" + usage.getCompletionTokens()
                    + "，总消耗tokens：" + usage.getTotalTokens());
            System.out.println("执行isDone方法结果:" + future.isDone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
