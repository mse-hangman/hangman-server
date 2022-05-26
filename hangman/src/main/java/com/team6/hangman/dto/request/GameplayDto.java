package com.team6.hangman.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameplayDto {
	public enum Type {
		CHECK, START, WORD, TURN, PLAY, RESULT
	}
	private Type type;
	private Integer gameroomId;
	
	private String counterpart;
	private String wordForCounterpart;
	private String user_nickname;	// for leaderboard
	private Integer diceNumber;
	
	private Boolean isCorrect;
	private Boolean isWin;	// for leaderboard
	
	//private Boolean isWord;
	//private String letter;
}
