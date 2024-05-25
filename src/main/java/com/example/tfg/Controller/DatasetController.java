package com.example.tfg.Controller;

import com.example.tfg.Model.Dataset;
import com.example.tfg.Model.DatasetContent;
import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import com.example.tfg.Service.DatasetContentService;
import com.example.tfg.Service.DatasetService;
import com.example.tfg.Service.DatasetWithUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class DatasetController {


    @Autowired
    private DatasetContentService datasetContentService ;
    @Autowired
    private DatasetService datasetService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public DatasetController(DatasetService datasetService, DatasetContentService datasetContentService, UserRepository userRepository) {
        this.datasetService = datasetService;
        this.datasetContentService = datasetContentService;
        this.userRepository = userRepository;
    }


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
    public ResponseEntity<?> createDataset(
            @RequestPart("dataset") Dataset dataset,
            @RequestPart("content") MultipartFile file,
            @RequestParam("fileType") String fileType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        Optional<Users> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Users user = userOptional.get();
        dataset.setProviderId(user.getId());

        Dataset savedDataset = datasetService.save(dataset);

        try {
            String content = new String(file.getBytes());
            DatasetContent datasetContent = new DatasetContent(savedDataset.getId(), content, fileType);
            datasetContentService.save(datasetContent);
            return ResponseEntity.ok(savedDataset);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al leer el archivo: " + e.getMessage());
        }
    }

    @GetMapping("/datasets/download/{id}")
    public ResponseEntity<Map<String, Object>> downloadDataset(@PathVariable String id) {
        Optional<Dataset> datasetOptional = datasetService.findById(id);
        if (!datasetOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Dataset dataset = datasetOptional.get();

        if ("public".equals(dataset.getAccess())) {
            Optional<DatasetContent> datasetContentOptional = datasetContentService.findById(id);
            if (datasetContentOptional.isPresent()) {
                DatasetContent datasetContent = datasetContentOptional.get();
                String content = datasetContent.getDatasetcontenido();
                String fileType = datasetContent.getFileType();
                String contentType;

                if ("json".equals(fileType)) {
                    contentType = "application/json";
                } else {
                    contentType = "text/csv";
                }

                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("content", content);
                responseBody.put("fileType", fileType);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }









}





