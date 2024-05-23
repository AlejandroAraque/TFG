package com.example.tfg.Repository;

import com.example.tfg.Model.AccessRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRequestRepository extends MongoRepository<AccessRequest, String> {
    List<AccessRequest> findByDatasetIdIn(List<String> datasetIds);
}
