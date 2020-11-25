package org.javaboy.vhr.model;

/**
 * @author oweson
 * @date 2020/10/25 22:00
 */


public class FishConstants {
    public static final Integer DELIVERING = 0;//消息投递中
    public static final Integer SUCCESS = 1;//消息投递成功
    public static final Integer FAILURE = 2;//消息投递失败
    public static final Integer MAX_TRY_COUNT = 3;//最大重试次数
    public static final Integer MSG_TIMEOUT = 1;//消息超时时间
    public static final String OWESON_FISH_QUEUE_NAME = "oweson.fish.queue";
    public static final String OWESON_EXCHANGE_NAME = "oweson.fish.exchange";
    public static final String OWESON_ROUTING_KEY_NAME = "oweson.fish.routing.key";
}
