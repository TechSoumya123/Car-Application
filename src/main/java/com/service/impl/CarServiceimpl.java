package com.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.CarDTO;
import com.exception.NotFoundException;
import com.model.Brand;
import com.model.Car;
import com.repository.BrandRepository;
import com.repository.CarRepository;
import com.service.CarService;
import com.utils.CarMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceimpl implements CarService {

	private final CarRepository carRepository;
	private final BrandRepository brandRepository;
	private final CarMapper carMapper;

	@Override
	public void saveCar(CarDTO carDTO) {
		Car car = carMapper.carDtoToCar(carDTO);
		Brand brand = brandRepository.findByName(carDTO.brand()).orElse(new Brand(carDTO.brand()));
		car.setBrand(brand);
		carRepository.save(car);
	}

	@Override
	public List<CarDTO> findAllCars() {
		return carRepository.findAll().stream()
				.map(carMapper::carToCarDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CarDTO findCarById(Long carId) {
		return carRepository.findById(carId).map(carMapper::carToCarDTO)
				.orElseThrow(() -> new NotFoundException("Sorry, Car not found!"));
	}

	@Override
	public void deleteCar(Long carId) {
		Optional.of(carId).ifPresent(carRepository::deleteById);
	}

}
