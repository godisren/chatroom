package chat.client;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class ClientStompApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	
    	String host = args.length>0 ? args[0] : "127.0.0.1";
    	String port = args.length>1 ? args[1] : "8080";
    	
    	String URL = "ws://"+host+":"+port+"/chat";
		System.out.println("Get ready to connect "+ URL);
    	
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new StringMessageConverter());
        
        StompSessionHandler sessionHandler = new ChatHandler();
        StompSession session = stompClient.connect(URL, sessionHandler).get();
        
        Scanner read = new Scanner(System.in);
        String str;
        while((str=read.nextLine())!=null) {
        	if("!exit".equals(str))
				break;
        	
        	session.send("/app/chat", str);
        }
    }
    
    public static class ChatHandler extends StompSessionHandlerAdapter{
    	@Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            System.out.println("New session established");
            session.subscribe("/topic/messages", this);
            System.out.println("Subscribed to /topic/messages");
        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            System.out.println("Got an exception. " + exception.getMessage());
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            System.out.println("> " + payload);
        }
    }
}
