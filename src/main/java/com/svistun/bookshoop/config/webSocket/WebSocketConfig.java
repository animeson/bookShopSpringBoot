package com.svistun.bookshoop.config.webSocket;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*@Configuration
@EnableWebSocketMessageBroker*/
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

/*    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue/", "/topic/");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }*/
}
