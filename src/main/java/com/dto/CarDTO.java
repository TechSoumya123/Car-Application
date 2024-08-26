package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarDTO(

		Long id,

		@NotBlank(message = "Car should have a model") String model,
		
		@NotNull(message = "Car should have a year") Integer year,
		
		@NotBlank(message = "Car should have a brand") String brand
		) {

}
