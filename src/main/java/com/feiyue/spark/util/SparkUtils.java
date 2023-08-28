package com.feiyue.spark.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feiyue.spark.client.SparkMessageText;
import com.feiyue.spark.client.SparkReqBuilder;
import com.feiyue.spark.client.req.SparkReq;
import com.feiyue.spark.config.SparkConfig;
import com.feiyue.spark.exception.SparkException;
import okhttp3.HttpUrl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author feiyue
 */
public class SparkUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 排除值为null的字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 对象转json字符串，使用 @JsonProperty注解指定json字段名
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SparkException(500, "对象转Json字符串失败");
        }
    }
    public static <V> V fromJson(String json, Class<V> type){
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new SparkException(500, "Json字符串转对象失败");
        }
    }

    public static SparkReq getSparkReqV1(List<SparkMessageText> messages,String uid, String chatId){
        return getSparkReq(messages,uid,chatId,SparkConfig.ParameterDomain.GENERAL);
    }

    public static SparkReq getSparkReqV2(List<SparkMessageText> messages,String uid, String chatId){
        return getSparkReq(messages,uid,chatId,SparkConfig.ParameterDomain.GENERALV2);
    }

    /**
     * 构造请求对象
     * @param messages
     * @param uid
     * @param chatId
     * @return
     */
    public static SparkReq getSparkReq(List<SparkMessageText> messages,String uid, String chatId,String domain) {
        // 构造请求
        return SparkReqBuilder.builder()
                //消息列表
                .messages(messages)
                //使用模型版本
                .domain(domain)
                //模型回答的tokens的最大长度,非必传,取值为[1,4096],默认为2048
                .maxTokens(2048)
                //核采样阈值。用于决定结果随机性,
                .temperature(0.2)
                //用户id
                .uid(uid)
                //用于关联用户会话,需要保障用户下的唯一性
                .chatId(chatId)
                .build();
    }

    /**
     * v1.1 授权
     * @return
     * @throws Exception
     */
    public static String getV1AuthUrl() throws Exception{
        return SparkUtils.getAuthUrl(SparkConfig.API_WSS_V1_1,
                SparkConfig.API_KEY,SparkConfig.API_SECRET);
    }

    /**
     * v2.1 授权
     * @return
     * @throws Exception
     */
    public static String getV2AuthUrl() throws Exception{
        return SparkUtils.getAuthUrl(SparkConfig.API_WSS_V2_1,
                SparkConfig.API_KEY,SparkConfig.API_SECRET);
    }

    /**
     * 根据domain判断授权
     * @return
     * @throws Exception
     */
    public static String getAuthUrl(String domain) throws Exception{
        if(SparkConfig.ParameterDomain.GENERALV2.equalsIgnoreCase(domain)){
            return getV2AuthUrl();
        }else {
            return getV1AuthUrl();
        }
    }

    /**
     * 鉴权方法
     * @param hostUrl
     * @param apiKey
     * @param apiSecret
     * @return
     * @throws Exception
     */
    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = new URL(hostUrl);
        // 时间
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        // 拼接
        String preStr = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        // System.err.println(preStr);
        // SHA256加密
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);

        byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
        // Base64加密
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        // System.err.println(sha);
        // 拼接
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        // 拼接地址
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder().
                addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8))).//
                addQueryParameter("date", date).
                addQueryParameter("host", url.getHost()).
                build();
        //System.out.println("hostUrl:"+hostUrl);
        return httpUrl.toString();
    }

    public static String getRandom(int length){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

}
