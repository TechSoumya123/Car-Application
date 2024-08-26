package com.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

	private String message;
	private LocalDateTime time;
}
