package com.feiyue.spark.client.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  "header": {
 *             "app_id": "12345",
 *             "uid": "12345"
 *   },
 * @author feiyue
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparkReqHeader implements Serializable {

    private static final long serialVersionUID = -8437677010991189282L;

    /**
     * app_id	string	必传		应用appid，从开放平台控制台创建的应用中获取
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * uid	string	非必传	最大长度32	每个用户的id，用于区分不同用户
     */
    private String uid;


}
