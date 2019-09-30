package chat.server;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping("/chat")
    @SendTo("/topic/messages")
	public String chatMessage(SimpMessageHeaderAccessor headerAccessor, String message) throws Exception {
		System.out.println("> "+message);
        return message + " (by "+headerAccessor.getSessionId()+" at "+new Date()+")";
    }
}
