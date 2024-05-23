package com.example.tfg.Service;

import com.example.tfg.Model.AccessRequest;
import com.example.tfg.Repository.AccessRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessRequestService {

    @Autowired
    private AccessRequestRepository accessRequestRepository;

    public List<AccessRequest> findAll() {
        return accessRequestRepository.findAll();
    }

    public Optional<AccessRequest> findById(String id) {
        return accessRequestRepository.findById(id);
    }

    public AccessRequest save(AccessRequest accessRequest) {
        return accessRequestRepository.save(accessRequest);
    }

    public void deleteById(String id) {
        accessRequestRepository.deleteById(id);
    }

}

