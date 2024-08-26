package com.service;

import java.util.List;

import com.dto.CarDTO;

public interface CarService {
	
	void saveCar(CarDTO carDTO);
	
	List<CarDTO> findAllCars();
	
	CarDTO findCarById(Long carId);
	
	void deleteCar(Long carId);
}
