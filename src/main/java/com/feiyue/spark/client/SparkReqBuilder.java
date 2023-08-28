package com.feiyue.spark.client;

import com.feiyue.spark.client.req.*;
import com.feiyue.spark.config.SparkConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feiyue
 * @date 2023/8/23
 */
public class SparkReqBuilder {

    private SparkReq req;

    public SparkReqBuilder() {
        req = new SparkReq();
        // header
        req.setHeader(new SparkReqHeader(SparkConfig.APPID,""));
        // parameter
        req.setParameter(new SparkReqParameter(new SparkReqParameterChat()));
        // payload
        req.setPayload(new SparkReqPayload(new SparkReqPayloadMessage(new ArrayList<>())));
    }

    public SparkReq build() {
        return req;
    }

    public SparkReq build(SparkReq req) {
        return this.req = req;
    }

    public static SparkReqBuilder builder() {
        return new SparkReqBuilder();
    }

    /**
     * 应用appId
     */
    public SparkReqBuilder appId(String appId) {
        req.getHeader().setAppId(appId);
        return this;
    }

    /**
     * 每个用户的id，用于区分不同用户<br/>
     * 非必传，最大长度32
     */
    public SparkReqBuilder uid(String uid) {
        req.getHeader().setUid(uid);
        return this;
    }

    /**
     * 消息列表，如果想获取结合上下文的回答，需要将历史问答信息放在一起<br/>
     * 必传，消息列表总tokens不能超过8192
     */
    public SparkReqBuilder messages(List<SparkMessageText> messages) {
        req.getPayload().getMessage().setText(messages);
        return this;
    }

    /**
     * 取值为[general,generalv2]
     * general指向V1.5版本
     * generalv2指向V2版本。
     */
    public SparkReqBuilder domain(String domain) {
        req.getParameter().getChat().setDomain(domain);
        return this;
    }

    /**
     * 核采样阈值。用于决定结果随机性,取值越高随机性越强即相同的问题得到的不同答案的可能性越高<br/>
     * 非必传,取值为[0,1],默认为0.5
     */
    public SparkReqBuilder temperature(Double temperature) {
        req.getParameter().getChat().setTemperature(temperature);
        return this;
    }

    /**
     * 模型回答的tokens的最大长度<br/>
     * 非必传,取值为[1,4096],默认为2048
     */
    public SparkReqBuilder maxTokens(Integer maxTokens) {
        req.getParameter().getChat().setMaxTokens(maxTokens);
        return this;
    }

    /**
     * 从k个候选中随机选择⼀个（⾮等概率）<br/>
     * 非必传,取值为[1,6],默认为4
     */
    public SparkReqBuilder topK(Integer topK) {
        req.getParameter().getChat().setTopK(topK);
        return this;
    }

    /**
     * 用于关联用户会话<br/>
     * 非必传,需要保障用户下的唯一性
     */
    public SparkReqBuilder chatId(String chatId) {
        req.getParameter().getChat().setChatId(chatId);
        return this;
    }

    /**
     * 覆盖默认的对话参数
     */
    public SparkReqBuilder chatParameter(SparkReqParameterChat parameterChat) {
        req.getParameter().setChat(parameterChat);
        return this;
    }


}
