package com.feiyue.spark.client.res;

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
public class SparkResPayloadUsage implements Serializable {
    private static final long serialVersionUID = -7058191420200033193L;

    private SparkResUsageText text;
}
