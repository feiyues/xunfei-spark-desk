package com.feiyue.spark.client.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * "payload":{
 *         "choices":{
 *             "status":2,
 *             "seq":0,
 *             "text":[
 *                 {
 *                     "content":"我可以帮助你的吗？",
 *                     "role":"assistant",
 *                     "index":0
 *                 }
 *             ]
 *         },
 *         "usage":{
 *             "text":{
 *                 "question_tokens":4,
 *                 "prompt_tokens":5,
 *                 "completion_tokens":9,
 *                 "total_tokens":14
 *             }
 *         }
 *     }
 * @author feiyue
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparkResPayload implements Serializable {
    private static final long serialVersionUID = 4226277195855771301L;

    private SparkResPayloadChoices choices;

    private SparkResPayloadUsage usage;


}
