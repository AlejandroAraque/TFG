package com.example.tfg.Service;

import com.example.tfg.Model.DatasetContent;
import com.example.tfg.Repository.DatasetContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatasetContentService {

    private final DatasetContentRepository datasetContentRepository;

    @Autowired
    public DatasetContentService(DatasetContentRepository datasetContentRepository) {
        this.datasetContentRepository = datasetContentRepository;
    }

    public DatasetContent save(DatasetContent datasetContent) {
        return datasetContentRepository.save(datasetContent);
    }

    public Optional<DatasetContent> findById(String id) {
        return datasetContentRepository.findById(id);
    }

    // Puedes agregar más métodos según sea necesario

}

