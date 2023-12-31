package application.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQconfig {
	
	@Value("${rabbitmq.queue.name}")
	public String queue;
	
	@Value("${rabbitmq.queue.json.name}")
	public String jsonqueue;
	
	@Value("${rabbitmq.exchange.name}")
	public String exchange;
	
	@Value("${rabbitmq.routing.key}")
	public String routingkey;
	
	@Value("${rabbitmq.routing.json.key}")
	public String jsonroutingkey;
	
	
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonqueue);
	}
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingkey);
	}
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonroutingkey);
	}
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}


}
