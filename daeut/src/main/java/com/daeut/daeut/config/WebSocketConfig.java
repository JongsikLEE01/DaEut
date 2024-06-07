package com.daeut.daeut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// @EnableWebSocket
// public class WebSocketConfig implements WebSocketConfigurer {
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
/*
 * @writer JSLee
 * @Date 2024-06-24
 * WebSocketConfigurer VS WebSocketMessageBrokerConfigurer
 * WebSocketConfigurer              -> Handler 직접 등록해 사용하고 싶을때
 *                                     특정 URL에 endpoint 추가하는데 사용
 * WebSocketMessageBrokerConfigurer -> STOMP 프로토콜을 이용
 *                                     메세지 브로커를 사용해 pub-sub 방식의 메세징 구현
 *                                     클라이언트가 특정 주제에 대해 구독하고 메세지를 발행
 *                                     메세지의 목적지 설정 가능, 엔드포인트 등록
 *                                     여러 사용자 간의 실시간 브로드캐스트에 유리
 */

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // webSocket의 handshake을 위한 엔드포인트 설정
        /**
         * addEndpoint          : 엔드포인트 설정
         * setAllowedOrigins    : 해당 경로에서 오는 WebScoket 요청만 허용
         * withSockJS           : SockJS를 사용하도록 설정
         *                        WebScoket 지원하지 않아도 통신을 가능하게해주는 폴리필 라이브러리
         */
        registry.addEndpoint("/chat")
                .withSockJS();
    }

    // Controller과 연결을 위한 브로커 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 클라이언트 -> 서버에 대한 엔드포인트 설정
        // 메세지 발행 요청, 메세지 보낼때
        config.setApplicationDestinationPrefixes("/pub");
        // 서버 -> 클라이언트에 대한 엔드포인트 설정 
        // 메세지 구독 요청, 메세지 받을때
        config.enableSimpleBroker("/sub");
    }
}
