package application.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import application.dto.User;

@Service
public class RabbitMQjsonProducer {
	@Value("${rabbitmq.exchange.name}")
	String exchange;
	@Value("${rabbitmq.routing.json.key}")
	String routingJsonkey;
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQjsonProducer.class);
	RabbitTemplate rabbitTemplate;

	public RabbitMQjsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	public void sendJsonMessage(User user) {
		LOGGER.info(String.format("Json message sent --> %s", user.toString()));
		rabbitTemplate.convertAndSend(exchange,routingJsonkey,user);
	}

}
