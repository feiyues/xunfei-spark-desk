package com.feiyue.spark.client;

import com.feiyue.spark.config.SparkConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author feiyue
 * @date 2023/8/23
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparkMessageText implements Serializable {

    private static final long serialVersionUID = 1151163282282351674L;
    /**
     * role	string	是
     * 取值为[user,assistant]
     * user表示是用户的问题，
     * assistant表示AI的回复
     */
    private String role;

    /**
     * content	string	是
     * 所有content的累计tokens需控制8192以内
     * 用户和AI的对话内容
     */
    private String content;

    /**
     * 响应参数，请求入参无
     * index	int	结果序号，取值为[0,10];
     * 当前为保留字段，开发者可忽略
     */
    private String index;

    /**
     * 创建用户消息
     *
     * @param content 内容
     */
    public static SparkMessageText userContent(String content) {
        return new SparkMessageText(SparkConfig.MessageRole.USER, content,null);
    }

    /**
     * 创建AI助手消息
     *
     * @param content 内容
     */
    public static SparkMessageText assistantContent(String content) {
        return new SparkMessageText(SparkConfig.MessageRole.ASSISTANT, content,null);
    }


}
