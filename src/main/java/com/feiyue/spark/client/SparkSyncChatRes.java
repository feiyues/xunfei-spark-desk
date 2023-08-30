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
     * 会话的唯一id
     * 用于讯飞技术人员查询服务端会话日志使用,出现调用错误时建议留存该字段
     */
    private String sid;

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

    /**
     * 错误码，0表示正常，非0表示出错；详细释义可在接口说明文档最后的错误码说明了解
     */
    private Integer code = 0;

    /**
     * message	string	会话是否成功的描述信息
     */
    private String message;

}
