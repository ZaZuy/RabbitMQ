package application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.publisher.RabbitMQProducer;
@RestController
@RequestMapping("/api/v1")
public class MessagerController {
	private RabbitMQProducer producer;
	public MessagerController(RabbitMQProducer producer) {
		this.producer=producer;
	}
	@GetMapping("/publish")
	public ResponseEntity<String> sentMessage(@RequestParam("message") String message){
		producer.sendMessage(message);
		return ResponseEntity.ok("Message sent to RabbitMQ " + message );
	}

}
