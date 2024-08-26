package com.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.dto.CarDTO;
import com.model.Car;

@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING, 
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

	@Mapping(target = "year", source = "yearOfManufacture")
	@Mapping(target = "brand", source = "brand.name")
	CarDTO carToCarDTO(Car car);

	@Mapping(target = "yearOfManufacture", source = "year")
	@Mapping(target = "brand", ignore = true)
	Car carDtoToCar(CarDTO carDTO);
}
