package com.example.tfg.Repository;

import com.example.tfg.Model.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetRepository extends MongoRepository<Dataset, String> {
    List<Dataset> findByProviderId(String providerId);
}
