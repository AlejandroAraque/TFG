package com.example.tfg.Service;

import com.example.tfg.Model.AccessRequest;
import com.example.tfg.Model.Dataset;
import com.example.tfg.Model.Users;
import com.example.tfg.Repository.AccessRequestRepository;
import com.example.tfg.Repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;
    @Autowired
    private AccessRequestRepository accessRequestRepository;


    public List<Dataset> findAll() {
        return datasetRepository.findAll();
    }

    public Optional<Dataset> findById(String id) {
        return datasetRepository.findById(id);
    }

    public Dataset save(Dataset dataset) {
        return datasetRepository.save(dataset);
    }
    public List<Dataset> getDatasetsByProviderId(String providerId) {
        return datasetRepository.findByProviderId(providerId);
    }
    public String getAuthenticatedProviderId() {
        // Assuming the user details have a method to get the providerId
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId(); // Or user.getProviderId() if available
    }
    public List<AccessRequest> getAccessRequestsForProvider(String providerId) {
        List<Dataset> datasets = getDatasetsByProviderId(providerId);
        List<String> datasetIds = datasets.stream()
                .map(Dataset::getId)
                .collect(Collectors.toList());
        return accessRequestRepository.findByDatasetIdIn(datasetIds);
    }


    public void deleteById(String id) {
        datasetRepository.deleteById(id);
    }
    }


