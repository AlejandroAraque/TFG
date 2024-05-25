package com.example.tfg.Repository;

import com.example.tfg.Model.DatasetContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatasetContentRepository extends MongoRepository<DatasetContent, String> {
    // Aquí puedes agregar métodos adicionales si es necesario
}
