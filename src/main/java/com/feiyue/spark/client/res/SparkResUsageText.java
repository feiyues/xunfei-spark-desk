package com.feiyue.spark.client.res;

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
public class SparkResUsageText implements Serializable {

    private static final long serialVersionUID = -1065099563864018594L;

    /**
     * question_tokens	int	保留字段，可忽略
     */
    @JsonProperty("question_tokens")
    private Integer questionTokens;

    /**
     * prompt_tokens	int	包含历史问题的总tokens大小
     */
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;

    /**
     * completion_tokens	int	回答的tokens大小
     */
    @JsonProperty("completion_tokens")
    private Integer completionTokens;

    /**
     * total_tokens	int	prompt_tokens和completion_tokens的和，也是本次交互计费的tokens大小
     */
    @JsonProperty("total_tokens")
    private Integer totalTokens;

}
