package com.binginx.myboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

@Slf4j
public class ProducerUtil {
    private static DefaultMQProducer producer;



    public static void init() throws Exception {
        log.info("开始启动消息生产者服务...");
        log.info("rocketmq消息组:"+ReadConfigProperties.getInstance().getConfig("rocketmq.producergroup")+"\n");
        log.info("rocketmq地址:"+ReadConfigProperties.getInstance().getConfig("rocketmq.namesrvaddr")+"\n");
        //创建一个消息生产者，并设置一个消息生产者组
        producer = new DefaultMQProducer(ReadConfigProperties.getInstance().getConfig("rocketmq.producergroup"));
        //指定 NameServer 地址
        producer.setNamesrvAddr(ReadConfigProperties.getInstance().getConfig("rocketmq.namesrvaddr"));
        //超时时间
        producer.setSendMsgTimeout(Integer.parseInt(ReadConfigProperties.getInstance().getConfig("rocketmq.sendMsgTimeout")));

        //初始化 SpringProducer，整个应用生命周期内只需要初始化一次
        producer.start();
        log.info("消息生产者服务启动成功.");
    }

    public static void destroy() {
        log.info("开始关闭消息生产者服务...");
        producer.shutdown();
        log.info("消息生产者服务已关闭.");
    }

    public static DefaultMQProducer getProducer() {
        return producer;
    }


    public static void sendQueue(String queueName,String messageStr) {
        log.info("开始发送消息到队列==========>>读取配置消息");
        //构建实例，第一个参数为topic,第二个参数为tabs,第三个参数为消息体
        try {
            Message message = new Message(ReadConfigProperties.getInstance().getConfig("rocketmq.topic"),queueName,messageStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //SendResult result = producer.send(message);
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("Product-异步发送-输出信息={}", sendResult);
                }
                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    //补偿机制，根据业务情况进行使用，看是否进行重试
                }
            });
            //log.info("发送完毕返回值==========>>"+result);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("\n\n\n异常信息打印:\n",e);
        }
    }
}
