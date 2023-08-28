# 科大讯飞星火认知大模型 SparkDesk JAVA-SDK
主要功能：
* 流式调用问答接口，控制台输出
* 同步调用问答接口，轮询等待，超时退出
* 采用Callable方式调用同步问答
* 全部请求参数和响应数据对象完整封装
* 响应状态、用户ID、用户chatId、tokens统计等
* 支持星火大模型API V1.5和V2.0两个版本
* 支持星火大模型服务全部错误码

## 引入项目依赖
建议直接下载项目源码集成到自己的业务系统中
```xml
<dependency>
    <groupId>com.feiyue.spark</groupId>
    <artifactId>xunfei-spark-desk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 星火大模型V1.5 
同步调用
```java
 SparkClient sparkClient = new SparkClient();

        // 消息列表，可以在此列表添加历史对话记录
        List<SparkMessageText> messages = new ArrayList<>();
        messages.add(SparkMessageText.userContent("我希望你充当知识专家，回答我的问题时简洁专业，不要有多余的解释。"));
        messages.add(SparkMessageText.assistantContent("好的，我会尽量简洁专业地回答你的问题。请问你有什么问题需要我解答？"));
        
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
```

控制台输出：
```bash
domain:general,
authWssUrl:wss://spark-api.xf-yun.com/v1.1/chat?authorization=YXBpX2tl
uid[U1001]轮询等待请求结果,当前次数:1
uid[U1001]轮询等待请求结果,当前次数:2
uid[U1001]轮询等待请求结果,当前次数:3
uid:[U1001], chatId:[U1001_1881], 开始提问：[{"role":"user","content":"我希望你充当知识专家，回答我的问题时简洁专业，不要有多余的解释。"},{"role":"assistant","content":"好的，我会尽量简洁专业地回答你的问题。请问你有什么问题需要我解答？"},{"role":"user","content":"写一首描述秋天的诗歌，100字以内"}]
uid:[U1001], chatId:[U1001_1881], 开始收到回答：
uid:[U1001], chatId:[U1001_1881], --content：秋 --完整响应：{"header":{"code":0,"message":"Success","status":0,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":0,"seq":0,"text":[{"role":"assistant","content":"秋","index":"0"}]}}}
uid:[U1001], chatId:[U1001_1881], --content：风起 --完整响应：{"header":{"code":0,"message":"Success","status":1,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":1,"seq":1,"text":[{"role":"assistant","content":"风起","index":"0"}]}}}
uid:[U1001], chatId:[U1001_1881], --content：，枫叶 --完整响应：{"header":{"code":0,"message":"Success","status":1,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":1,"seq":2,"text":[{"role":"assistant","content":"，枫叶","index":"0"}]}}}
uid[U1001]轮询等待请求结果,当前次数:4
uid:[U1001], chatId:[U1001_1881], --content：红。
霜 --完整响应：{"header":{"code":0,"message":"Success","status":1,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":1,"seq":3,"text":[{"role":"assistant","content":"红。\n霜","index":"0"}]}}}
uid:[U1001], chatId:[U1001_1881], --content：降后，天气凉 --完整响应：{"header":{"code":0,"message":"Success","status":1,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":1,"seq":4,"text":[{"role":"assistant","content":"降后，天气凉","index":"0"}]}}}
uid[U1001]轮询等待请求结果,当前次数:5
uid[U1001]轮询等待请求结果,当前次数:6
uid:[U1001], chatId:[U1001_1881], --content：。
 --完整响应：{"header":{"code":0,"message":"Success","status":1,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":1,"seq":5,"text":[{"role":"assistant","content":"。\n","index":"0"}]}}}
uid[U1001]轮询等待请求结果,当前次数:7
uid[U1001]轮询等待请求结果,当前次数:8
uid[U1001]轮询等待请求结果,当前次数:9
uid:[U1001], chatId:[U1001_1881], --content：收获时节，稻谷黄。
桂花香，月色清。
蓝天白云，秋日晴。
 --完整响应：{"header":{"code":0,"message":"Success","status":1,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":1,"seq":6,"text":[{"role":"assistant","content":"收获时节，稻谷黄。\n桂花香，月色清。\n蓝天白云，秋日晴。\n","index":"0"}]}}}
uid[U1001]轮询等待请求结果,当前次数:10
uid[U1001]轮询等待请求结果,当前次数:11
uid:[U1001], chatId:[U1001_1881], --content：人们收割，心中喜。
感恩大地，馈赠丰盈。
 --完整响应：{"header":{"code":0,"message":"Success","status":1,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":1,"seq":7,"text":[{"role":"assistant","content":"人们收割，心中喜。\n感恩大地，馈赠丰盈。\n","index":"0"}]}}}
uid[U1001]轮询等待请求结果,当前次数:12
uid:[U1001], chatId:[U1001_1881], --content：秋天美景，令人陶醉。 --完整响应：{"header":{"code":0,"message":"Success","status":2,"sid":"cht000b1cee@dx18a2c308bcab8f2540"},"payload":{"choices":{"status":2,"seq":8,"text":[{"role":"assistant","content":"秋天美景，令人陶醉。","index":"0"}]},"usage":{"text":{"question_tokens":21,"prompt_tokens":69,"completion_tokens":68,"total_tokens":137}}}}
uid:[U1001], chatId:[U1001_1881], 最后一条结果，关闭webSocket连接
uid[U1001]轮询等待请求结果,当前次数:13

完整回答：秋风起，枫叶红。
霜降后，天气凉。
收获时节，稻谷黄。
桂花香，月色清。
蓝天白云，秋日晴。
人们收割，心中喜。
感恩大地，馈赠丰盈。
秋天美景，令人陶醉。
提问消耗tokens：69，回答消耗tokens：68，总消耗tokens：137

Process finished with exit code 0

```

## 星火大模型V2.1 同步调用

```java
SparkClient sparkClient = new SparkClient();

// 消息列表，可以在此列表添加历史对话记录
List<SparkMessageText> messages = new ArrayList<>();
messages.add(SparkMessageText.userContent("我希望你充当知识专家，回答我的问题时简洁专业，不要有多余的解释。"));
messages.add(SparkMessageText.assistantContent("好的，我会尽量简洁专业地回答你的问题。请问你有什么问题需要我解答？"));
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
```

## 星火大模型Callable+线程池同步调用

```java
try {
    SparkClient sparkClient = new SparkClient();

    // 消息列表，可以在此列表添加历史对话记录
    List<SparkMessageText> messages = new ArrayList<>();
    messages.add(SparkMessageText.userContent("我希望你充当知识专家，回答我的问题时简洁专业，不要有多余的解释。"));
    messages.add(SparkMessageText.assistantContent("好的，我会尽量简洁专业地回答你的问题。请问你有什么问题需要我解答？"));

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
```
