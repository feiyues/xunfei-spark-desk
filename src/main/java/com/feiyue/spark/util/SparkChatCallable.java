package com.feiyue.spark.util;

import com.feiyue.spark.client.SparkClient;
import com.feiyue.spark.client.SparkSyncChatRes;
import com.feiyue.spark.client.req.SparkReq;

import java.util.concurrent.Callable;

/**
 * @author feiyue
 * @date 2023/8/24
 */
public class SparkChatCallable implements Callable<SparkSyncChatRes> {

    private SparkReq req;
    private SparkClient sparkClient;

    public SparkReq getReq() {
        return req;
    }

    public void setReq(SparkReq req) {
        this.req = req;
    }

    public SparkChatCallable() {
    }

    public SparkChatCallable(SparkReq req,SparkClient sparkClient) {
        this.req = req;
        this.sparkClient = sparkClient;
    }

    @Override
    public SparkSyncChatRes call() {
        return sparkClient.chatSync(req);
    }
}
