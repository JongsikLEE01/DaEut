package com.daeut.daeut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // endpoint 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // webSocket의 handshake을 위한 엔드포인트 설정
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    // Controller과 연결을 위한 브로커 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 서버 -> 클라이언트에 대한 엔드포인트 설정
        config.enableSimpleBroker("/sub");
        // 클라이언트 -> 서버에 대한 엔드포인트 설정
        config.setApplicationDestinationPrefixes("/pub");
    }
}
