package com.example.demo.repository;

import com.example.demo.model.CityVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository <CityVO,
        String> {
}