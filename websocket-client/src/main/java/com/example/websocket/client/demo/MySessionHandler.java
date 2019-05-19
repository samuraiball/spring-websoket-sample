package com.example.websocket.client.demo;

import com.example.websocket.client.HelloMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

public class MySessionHandler extends StompSessionHandlerAdapter {


    private Logger logger = LogManager.getLogger(MySessionHandler.class);
    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {

        logger.info("New session established : " + stompSession.getSessionId());

        stompSession.subscribe("/topic/greetings", this);
        stompSession.send("/app/hello", getMassage());
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {

    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {

    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        Greeting msg = (Greeting) o;
        logger.info(msg.getContent());
    }

    private HelloMessage getMassage(){
       return new HelloMessage("testaaaaaaaa");
    }
}
