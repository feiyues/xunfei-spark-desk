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
public class SparkReq  implements Serializable {

    private static final long serialVersionUID = 6770303849978965299L;

    /**
     *  请求header
     */
    private SparkReqHeader header;

    /**
     * 请求parameter
     */
    private SparkReqParameter parameter;

    /**
     * 请求payload
     */
    private SparkReqPayload payload;

}
