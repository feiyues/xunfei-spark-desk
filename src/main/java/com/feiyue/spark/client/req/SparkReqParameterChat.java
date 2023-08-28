package com.feiyue.spark.client.req;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SparkReqParameterChat  implements Serializable {

    private static final long serialVersionUID = 8136330616600062605L;
    /**
     * domain	string	是
     * 取值为[general,generalv2]
     * 指定访问的领域,
     * general指向V1.5版本  ws(s)://spark-api.xf-yun.com/v1.1/chat
     * generalv2指向V2版本。 ws(s)://spark-api.xf-yun.com/v2.1/chat
     * 注意：不同的取值对应的url也不一样！
     */
    @JsonProperty("domain")
    private String domain = "general";

    /**
     * temperature	float	否
     * 取值为[0,1],默认为0.5
     * 核采样阈值。用于决定结果随机性，取值越高随机性越强即相同的问题得到的不同答案的可能性越高
     */
    @JsonProperty("temperature")
    private Double temperature;

    /**
     * max_tokens	int	否
     * 取值为[1,4096]，默认为2048
     * 模型回答的tokens的最大长度
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * top_k	int	否
     * 取值为[1，6],默认为4
     * 从k个候选中随机选择⼀个（⾮等概率）
     */
    @JsonProperty("top_k")
    private Integer topK;

    /**
     * chat_id	string	否
     * 需要保障用户下的唯一性
     * 用于关联用户会话
     */
    @JsonProperty("chat_id")
    private String chatId;

}
