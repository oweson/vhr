package org.javaboy.vhr.config;

import org.javaboy.vhr.model.FishConstants;
import org.javaboy.vhr.model.MailConstants;
import org.javaboy.vhr.service.MailSendLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public final static Logger logger = LoggerFactory.getLogger(RabbitConfig.class);
    @Autowired
    CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    MailSendLogService mailSendLogService;

    @Bean
    RabbitTemplate rabbitTemplate() {
        // 1 发送失败；发送成功，是否准确投递到queue无法保证
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        // 2 data:消息id,ack:是否成功，cause:原因
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                logger.info(msgId + ":消息发送成功");
                mailSendLogService.updateMailSendLogStatus(msgId, 1);
                //修改数据库中的记录，消息投递成功
            } else {
                logger.info(msgId + ":消息发送失败");
            }
        });
        // 2 交换机路由到queue的时候失败！！！
        rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingkey) -> {
            logger.info("消息发送失败");
        });
        return rabbitTemplate;
    }

    @Bean
    Queue mailQueue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange mailExchange() {
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }

    //-------------------------------------自定义的配置---------------------------------------
    @Bean
    Queue fishQueue() {
        return new Queue(FishConstants.OWESON_FISH_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange fishExchange() {
        return new DirectExchange(FishConstants.OWESON_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding fishBinding() {
        return BindingBuilder.bind(fishQueue()).to(fishExchange()).with(FishConstants.OWESON_ROUTING_KEY_NAME);
    }

}
