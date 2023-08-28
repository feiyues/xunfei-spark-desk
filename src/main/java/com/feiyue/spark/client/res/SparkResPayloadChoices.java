package com.feiyue.spark.client.res;

import com.feiyue.spark.client.SparkMessageText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author feiyue
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparkResPayloadChoices implements Serializable {
    private static final long serialVersionUID = -4734185354374330157L;

    /**
     * status	int
     * 文本响应状态，取值为[0,1,2]; 0代表首个文本结果；1代表中间文本结果；2代表最后一个文本结果
     */
    private Integer status;

    /**
     * seq	int	返回的数据序号，取值为[0,9999999]
     */
    private Integer seq;

    /**
     * AI的回答内容列表
     */
    private List<SparkMessageText> text;

}
