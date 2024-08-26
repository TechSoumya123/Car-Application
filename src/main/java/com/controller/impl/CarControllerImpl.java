package com.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.controller.CarController;
import com.dto.CarDTO;
import com.exception.NotFoundException;
import com.service.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CarControllerImpl implements CarController {

	private final CarService carService;
	final static Logger logger = LoggerFactory.getLogger(CarControllerImpl.class);

	@Override
	public ResponseEntity<Void> saveCar(CarDTO carDTO) {
		try {
			logger.info("Save request with CarDto {}:", carDTO);
			carService.saveCar(carDTO);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception ex) {
			logger.info("Error occured during save the car {},{}", carDTO, ex.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<?> findById(Long carId) {
		try {
			var carDTO = carService.findCarById(carId);
			return ResponseEntity.ok(carDTO);
		} catch (NotFoundException exception) {
			logger.error("\n Car with id: {} not found,{}", carId, exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error occurred while fetching car with id: {},{}", carId, exception.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<CarDTO>> findAllCars() {
		try {
			var allCars = carService.findAllCars();
			return allCars.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allCars);
		} catch (Exception ex) {
			logger.error("\n Error occured during fetch all cars from server {}", ex.getLocalizedMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<?> deleteCar(Long carId) {
		try {
			carService.deleteCar(carId);
			return ResponseEntity.ok("Car deleted successfully");
		} catch (NotFoundException exception) {
			logger.error("\n Car with id: {} not found,{}", carId, exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error occurred while deleting car with id: {},{}", carId, exception.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

}
