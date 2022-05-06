package com.team6.hangman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team6.hangman.dto.request.GameplayDto;


public class WebSocketHandler extends TextWebSocketHandler{
	private List<WebSocketSession> players = new ArrayList<WebSocketSession>();
	private Integer gameroomId;
	private List<Integer> gameroomNumbers = new ArrayList<Integer>();
	private ObjectMapper objectMapper;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Player entered");
		players.add(session);
		//System.out.println("inside session:"+session);
		//System.out.println(session.getUri().toString());
		String[] uriArray = session.getUri().toString().split("/");
		gameroomId = Integer.valueOf(uriArray[4]);
		System.out.println(gameroomId);
		
		if(gameroomNumbers.indexOf(gameroomId) == -1) {
			gameroomNumbers.add(gameroomId);
			GameplayManager gameManager = new GameplayManager(gameroomId);
		}
		
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String receivedMessage = message.getPayload();
		System.out.println(receivedMessage);
		
		GameplayDto gameplayDto = objectMapper.readValue(receivedMessage, GameplayDto.class);
		//GameplayManager gameManager = GameplayService.findManagerById(gameroomId);
		//gameManager.handleActions();
		
		for (WebSocketSession player : players) {
			player.sendMessage(new TextMessage(receivedMessage));
		}
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("Player left");
		players.remove(session);
	}

}