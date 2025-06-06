package com.ss.spring_security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDto {

	private Long id;

	private String title;

	private String description;

	private boolean status;
}
