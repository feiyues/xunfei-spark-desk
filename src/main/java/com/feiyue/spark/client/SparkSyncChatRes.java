package com.feiyue.spark.client;

import com.feiyue.spark.client.res.SparkResUsageText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author feiyue
 * @date 2023/8/24
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparkSyncChatRes {

    /**
     * 模型回复内容
     */
    private String content;

    /**
     * 消耗tokens统计
     */
    private SparkResUsageText usage;

    /**
     * 回复是否已完成
     */
    private boolean complete = false;


}
