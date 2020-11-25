package sea.top.mq;

import org.javaboy.vhr.VhrApplicationTests;
import org.javaboy.vhr.model.FishConstants;
import org.junit.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author oweson
 * @date 2020/10/25 22:34
 */


public class FishRabbit extends VhrApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void demmo1() {
        rabbitTemplate.convertAndSend(FishConstants.OWESON_EXCHANGE_NAME, FishConstants.OWESON_ROUTING_KEY_NAME,
                UUID.randomUUID().toString(), new CorrelationData(UUID.randomUUID().toString()));
    }


}
