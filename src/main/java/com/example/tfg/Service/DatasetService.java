package com.example.tfg.Service;

import com.example.tfg.DTO.EnrichedAccessRequestDTO;
import com.example.tfg.DTO.RequestedAccessRequestDTO;
import com.example.tfg.Model.AccessRequest;
import com.example.tfg.Model.Dataset;
import com.example.tfg.Model.Users;
import com.example.tfg.Repository.AccessRequestRepository;
import com.example.tfg.Repository.DatasetRepository;
import com.example.tfg.Repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;


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
    public List<EnrichedAccessRequestDTO> getEnrichedAccessRequestsForProvider(String providerId) {
        List<Dataset> datasets = getDatasetsByProviderId(providerId);
        List<String> datasetIds = datasets.stream()
                .map(Dataset::getId)
                .collect(Collectors.toList());
        List<AccessRequest> accessRequests = accessRequestRepository.findByDatasetIdIn(datasetIds);

        return accessRequests.stream().map(request -> {
            EnrichedAccessRequestDTO dto = new EnrichedAccessRequestDTO();
            dto.setId(request.getId());
            dto.setDatasetId(request.getDatasetId());

            // Get dataset details
            Dataset dataset = datasetRepository.findById(request.getDatasetId()).orElse(null);
            if (dataset != null) {
                dto.setDatasetName(dataset.getName());
                dto.setDatasetDescription(dataset.getDescription());
                dto.setDatasetPrice(dataset.getPrice());
                dto.setDatasetDate(dataset.getDate());
                dto.setDatasetAccessTerms(dataset.getAccessTerms());
                dto.setDatasetTermsOfUse(dataset.getTermsOfUse());
            }

            // Get consumer details
            Users consumer = userRepository.findById(request.getConsumerId()).orElse(null);
            if (consumer != null) {
                dto.setConsumerUsername(consumer.getUsername());
            }
            dto.setConsumerId(request.getConsumerId());
            dto.setStatus(request.getStatus());
            dto.setMessage(request.getMessage());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<RequestedAccessRequestDTO> getRequestedAccessRequestsForUser(String userId) {
        List<AccessRequest> accessRequests = accessRequestRepository.findByConsumerId(userId);

        return accessRequests.stream().map(request -> {
            RequestedAccessRequestDTO dto = new RequestedAccessRequestDTO();
            dto.setId(request.getId());
            dto.setDatasetId(request.getDatasetId());

            // Get dataset details
            Dataset dataset = datasetRepository.findById(request.getDatasetId()).orElse(null);
            if (dataset != null) {
                dto.setDatasetName(dataset.getName());
                dto.setDatasetDescription(dataset.getDescription());
                dto.setDatasetPrice(dataset.getPrice());
                dto.setDatasetDate(dataset.getDate());
                dto.setDatasetTermsOfUse(dataset.getTermsOfUse());
            }

            dto.setStatus(request.getStatus());
            dto.setMessage(request.getMessage());
            return dto;
        }).collect(Collectors.toList());
    }

    public void deleteById(String id) {
        datasetRepository.deleteById(id);
    }
    }


