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
public class SparkReqParameter  implements Serializable {
    private static final long serialVersionUID = -1707063866482701720L;

    private SparkReqParameterChat chat;

}
