package com.feiyue.spark.client.req;

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
public class SparkReqPayloadMessage implements Serializable {

    private static final long serialVersionUID = -2090807286418990886L;
    /**
     * 请求消息，包含历史会话
     *
     */
    private List<SparkMessageText> text;
}
