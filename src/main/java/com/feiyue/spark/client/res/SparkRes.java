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
public class SparkRes implements Serializable {
    private static final long serialVersionUID = -3034057172506374464L;

    private SparkResHeader header;

    private SparkResPayload payload;
}
