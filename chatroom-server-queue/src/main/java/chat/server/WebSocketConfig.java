package chat.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Value("${mq.host}")
    private String mqHost;
	
	@Value("${mq.port}")
    private Integer mqPort;
	
	@Value("${mq.username}")
    private String username;
	
	@Value("${mq.password}")
    private String password;
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
		System.out.println("mqHost:"+mqHost);
		
		//config.enableSimpleBroker("/topic");
		config.enableStompBrokerRelay("/topic")
	        .setRelayHost(mqHost)
	        .setRelayPort(mqPort)
	        .setClientLogin(username)
	        .setClientPasscode(password);
        config.setApplicationDestinationPrefixes("/app");
        
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	registry.addEndpoint("/chat")
        	.setAllowedOrigins("*");
    }
   
}
