package drugsintel.m.service;

import java.util.List;

import drugsintel.m.dto.MessageDto;
import drugsintel.m.dto.MessageRequestDto;
import drugsintel.m.model.Message;


public interface MService {

	
	MessageRequestDto writeMessage(MessageDto messageDto, String sender, String receiver);
	
	List<MessageRequestDto> getAllMessagesforUser(String user);
	
	List<MessageRequestDto> getAllUnreadMessagesforUser(String user);
	
	Message readMessage(String user);
	
	MessageRequestDto deleteMessage(String id);
	

}
