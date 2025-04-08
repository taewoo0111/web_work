package com.example.spring17.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MySocketHandler extends TextWebSocketHandler{
	
	List<WebSocketSession> sessionList = Collections.synchronizedList(new ArrayList<>());
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		System.out.println(sessionId + "연결");
		TextMessage message = new TextMessage("안녕 클라이언트야!");
		session.sendMessage(message);
		sessionList.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message.getPayload());
		sessionList.forEach((item)->{
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String sessionId = session.getId();
		System.out.println(sessionId + "연결해제됨!");
		sessionList.remove(session);
	}
}
