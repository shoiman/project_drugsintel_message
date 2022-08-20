package drugsintel.m.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import drugsintel.m.dto.MessageDto;
import drugsintel.m.dto.MessageRequestDto;
import drugsintel.m.model.Message;
import drugsintel.m.service.MessageService;



@RestController
@RequestMapping("/messagebox")
public class MessageController {
	
	MessageService messageService;	
	RestTemplate restTemplate;

	@Autowired
	public MessageController(MessageService messageService, RestTemplate restTemplate) {
		this.messageService = messageService;
		this.restTemplate = restTemplate;
	}
	
		
	@PostMapping("/add/{sender}/{reseiver}")
	public MessageRequestDto addMessage(@RequestBody MessageDto messageDto,@PathVariable String sender,@PathVariable String reseiver) {
		return messageService.writeMessage(messageDto, sender, reseiver);
	}
	
	@GetMapping("/get/{reseiver}")
	public Message getMessage(@PathVariable String reseiver) {
		return messageService.readMessage(reseiver);
	}
	
	@GetMapping("/getAllForUser/{user}")
	public List<MessageRequestDto> getAllMessagesByUser(@PathVariable String user){
		return messageService.getAllMessagesforUser(user);
	}
	
	@GetMapping("/getAllUnreadMessageForUser/{user}")
	public List<MessageRequestDto> getAllUnreadMessagesByUser(@PathVariable String user){
		return messageService.getAllUnreadMessagesforUser(user);
	}

	@DeleteMapping("delete/{id}")
	public MessageRequestDto deleteMessage(@PathVariable String id) {
		return messageService.deleteMessage(id);
	}
	
	
	//for micro
	@GetMapping("/username/{username}") 
	public Object getUser(@PathVariable String username) {
		System.out.println(username);
		String url = "http://auth-microservice/accounting/username/" + username;
		System.out.println(url);
		Object objects = restTemplate.getForObject(url, Object.class);
		return objects;
	}
}
