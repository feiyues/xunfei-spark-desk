/**
 * Copyright (C) 2018-2020
 * All rights reserved
 *
 */
package com.feiyue.spark.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 星火大模型服务错误码
 * @author feiyue
 */
@Getter
@AllArgsConstructor
public enum SparkErrorEnum {
    SUCCESS(0, "成功"),
    CODE_10000(10000, "升级为ws出现错误"),
    CODE_10001(10001, "通过ws读取用户的消息出错"),
    CODE_10002(10002, "通过ws向用户发送消息错误"),
    CODE_10003(10003, "用户的消息格式有错误"),
    CODE_10004(10004, "用户数据的schema错误"),
    CODE_10005(10005, "用户参数值有错误"),
    CODE_10006(10006, "用户并发错误：当前用户已连接，同一用户不能多处同时连接"),
    CODE_10007(10007, "用户流量受限：服务正在处理用户当前的问题，需等待处理完成后再发送新的请求"),
    CODE_10008(10008, "服务容量不足，联系工作人员"),
    CODE_10009(10009, "和引擎建立连接失败"),
    CODE_10010(10010, "接收引擎数据的错误"),
    CODE_10011(10011, "发送数据给引擎的错误"),
    CODE_10012(10012, "引擎内部错误"),
    CODE_10013(10013, "输入内容审核不通过，涉嫌违规，请重新调整输入内容"),
    CODE_10014(10014, "输出内容涉及敏感信息，审核不通过，后续结果无法展示给用户"),
    CODE_10015(10015, "appid在黑名单中"),
    CODE_10016(10016, "appid授权类的错误。未开通此功能，未开通对应版本，token不足，并发超过授权等等"),
    CODE_10017(10017, "清除历史失败"),
    CODE_10019(10019, "表示本次会话内容有涉及违规信息的倾向；建议开发者收到此错误码后给用户一个输入涉及违规的提示"),
    CODE_10110(10110, "服务忙，请稍后再试"),
    CODE_10163(10163, "请求引擎的参数异常，引擎的schema检查不通过"),
    CODE_10222(10222, "引擎网络异常"),
    CODE_10907(10907, "token数量超过上限。对话历史+问题的字数太多，需要精简输入"),
    CODE_11200(11200, "授权错误：该appId没有相关功能的授权或者业务量超过限制"),
    CODE_11201(11201, "授权错误：日流控超限。超过当日最大访问量的限制"),
    CODE_11202(11202, "授权错误：秒级流控超限。秒级并发超过授权路数限制"),
    CODE_11203(11203, "授权错误：并发流控超限。并发路数超过授权路数限制"),

    ;

    private Integer code;
    private String msg;

    public static SparkErrorEnum getByCode(Integer code) {
        return Stream.of(SparkErrorEnum.values())
                .filter(c -> c.code.equals(code))
                .findAny()
                .orElse(null);
    }

    public static String getMsgByCode(Integer code) {
        SparkErrorEnum error = getByCode(code);
        if(error != null){
            return error.getMsg();
        }
        return "";
    }

}
