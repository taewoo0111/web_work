package com.example.spring05.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoDto {
	private int id;
	private String todo;
	private String time;
}
