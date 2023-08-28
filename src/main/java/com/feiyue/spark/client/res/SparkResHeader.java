package com.feiyue.spark.client.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  "header":{
 *         "code":0,
 *         "message":"Success",
 *         "sid":"cht000cb087@dx18793cd421fb894542",
 *         "status":2
 *  },
 * @author feiyue
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparkResHeader implements Serializable {

    private static final long serialVersionUID = 197039678396688651L;

    /**
     * code	int
     * 错误码，0表示正常，非0表示出错；详细释义可在接口说明文档最后的错误码说明了解
     */
    private Integer code;

    /**
     * message	string	会话是否成功的描述信息
     */
    private String message;

    /**
     * status	int
     * 会话状态，取值为[0,1,2]；0代表首次结果；1代表中间结果；2代表最后一个结果
     */
    private Integer status;

    /**
     * sid	string
     * 会话的唯一id，用于讯飞技术人员查询服务端会话日志使用,出现调用错误时建议留存该字段
     */
    private String sid;

}
