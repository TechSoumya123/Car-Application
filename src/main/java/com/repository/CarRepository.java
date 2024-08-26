package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
