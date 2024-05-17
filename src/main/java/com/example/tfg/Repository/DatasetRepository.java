package com.example.tfg.Repository;

import com.example.tfg.Model.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetRepository extends MongoRepository<Dataset, String> {
}
