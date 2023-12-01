package application.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import application.dto.User;
import application.publisher.RabbitMQjsonProducer;

@Service
public class RabbitJsonConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitJsonConsumer.class);
	@RabbitListener(queues= {"${rabbitmq.queue.json.name}"})
	public void consume(User user) {
		LOGGER.info(String.format("Received-->%s", user.getId()+user.getFirstname()+ user.getLastname()));
	}
}
