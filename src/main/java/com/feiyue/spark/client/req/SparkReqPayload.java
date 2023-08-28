package com.feiyue.spark.client.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author feiyue
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparkReqPayload implements Serializable {

    private static final long serialVersionUID = -4243611569642367456L;

    /**
     * "payload": {
     *             "message": {
     *                 # 如果想获取结合上下文的回答，需要开发者每次将历史问答信息一起传给服务端，如下示例
     *                 # 注意：text里面的所有content内容加一起的tokens需要控制在8192以内，开发者如有较长对话需求，需要适当裁剪历史信息
     *                 "text": [
     *                     {"role": "user", "content": "你是谁"} # 用户的历史问题
     *                     {"role": "assistant", "content": "....."}  # AI的历史回答结果
     *                     # ....... 省略的历史对话
     *                     {"role": "user", "content": "你会做什么"}  # 最新的一条问题，如无需上下文，可只传最新一条问题
     *                 ]
     *         }
     */
    private SparkReqPayloadMessage message;
}
