package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.CarDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RequestMapping(path = { "/api/v1/car" })
public interface CarController {

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable("id") @Min(value = 1,message = "Car id should be grater than 0") final Long carId);

	@GetMapping(path = { "/All" })
	public ResponseEntity<List<CarDTO>> findAllCars();

	@PostMapping(path = { "/create" })
	public ResponseEntity<Void> saveCar(@RequestBody @Valid CarDTO carDTO);

	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<?> deleteCar(@PathVariable("id") final Long carId);
}
