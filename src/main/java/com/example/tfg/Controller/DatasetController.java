package com.example.tfg.Controller;

import com.example.tfg.Model.Dataset;
import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import com.example.tfg.Service.AccessRequestService;
import com.example.tfg.Service.DatasetService;
import com.example.tfg.Service.DatasetWithUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class DatasetController {


    @Autowired
    private AccessRequestService accessRequestService;
    @Autowired
    private DatasetService datasetService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/datasets")
    public List<DatasetWithUsername> getAllDatasets() {
        List<Dataset> datasets = datasetService.findAll();
        return datasets.stream().map(dataset -> {
            Optional<Users> userOptional = userRepository.findById(dataset.getProviderId());
            String username = userOptional.map(Users::getUsername).orElse("Unknown");
            return new DatasetWithUsername(dataset, username);
        }).collect(Collectors.toList());
    }


    @GetMapping("/myDatasets")
    public List<Dataset> getDatasetsByAuthenticatedProvider() {
        String providerId = datasetService.getAuthenticatedProviderId();
        return datasetService.getDatasetsByProviderId(providerId);
    }



        @PostMapping("/datasets")
        public ResponseEntity<Dataset> createDataset(@RequestBody Dataset dataset) {
            // Obtener la autenticación actual
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                // Obtener el nombre de usuario del usuario autenticado
                String username = authentication.getName();

                // Buscar al usuario en la base de datos por el nombre de usuario
                Optional<Users> userOptional = userRepository.findByUsername(username);
                if (userOptional.isPresent()) {
                    Users user = userOptional.get();

                    // Asignar el ID del usuario autenticado como providerId
                    dataset.setProviderId(user.getId());

                    // Guardar el dataset en la base de datos
                    Dataset savedDataset = datasetService.save(dataset);

                    return ResponseEntity.ok(savedDataset);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }


    }





