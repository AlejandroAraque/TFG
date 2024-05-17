package com.example.tfg.Service;

import com.example.tfg.Model.Dataset;
import com.example.tfg.Repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;

    public List<Dataset> findAll() {
        return datasetRepository.findAll();
    }

    public Optional<Dataset> findById(String id) {
        return datasetRepository.findById(id);
    }

    public Dataset save(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    public void deleteById(String id) {
        datasetRepository.deleteById(id);
    }
}
