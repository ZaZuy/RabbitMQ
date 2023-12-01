package application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.dto.User;
import application.publisher.RabbitMQjsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJson {
	RabbitMQjsonProducer mQjsonProducer;
	public MessageJson(RabbitMQjsonProducer mQjsonProducer) {
		this.mQjsonProducer=mQjsonProducer;
	}
	@PostMapping("publish")
	public ResponseEntity<String> seResponseEntity(@RequestBody User user){
		mQjsonProducer.sendJsonMessage(user);
		return ResponseEntity.ok("json mess sent to Rabbit");
	}

}
