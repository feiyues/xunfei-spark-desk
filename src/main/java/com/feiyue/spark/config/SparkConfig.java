package com.feiyue.spark.config;

/**
 * @author feiyue
 */
public interface SparkConfig {

    String APPID = "xxx";

    String API_KEY = "xxx";

    String API_SECRET = "xxx";

    /**
     * 星火v1.1地址：wss协议
     */
    String API_WSS_V1_1 = "https://spark-api.xf-yun.com/v1.1/chat";
    /**
     * 星火v2.1地址：wss协议
     */
    String API_WSS_V2_1 = "https://spark-api.xf-yun.com/v2.1/chat";

    /**
     * 取值为[general,generalv2]
     * general 指向V1.5版本
     * generalv2 指向V2版本。
     */
    interface ParameterDomain{
        String GENERAL = "general";
        String GENERALV2 = "generalv2";
    }

    interface MessageRole{
        /**
         * 系统
         */
        String SYSTEM = "system";

        /**
         * 用户
         */
        String USER = "user";

        /**
         * 机器人助手
         */
        String ASSISTANT = "assistant";
    }

    /**
     *
     * code 错误码，0表示正常，非0表示出错；详细释义可在接口说明文档最后的错误码说明了解
     */
    interface ResCode{
        Integer SUCCESS = 0;
    }

}
