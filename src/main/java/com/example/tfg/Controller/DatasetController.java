package com.example.tfg.Controller;

import com.example.tfg.Model.Dataset;
import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import com.example.tfg.Service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class DatasetController {

    @Autowired
    private DatasetService datasetService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Dataset> getAllDatasets() {
        return datasetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dataset> getDatasetById(@PathVariable String id) {
        Optional<Dataset> dataset = datasetService.findById(id);
        return dataset.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("/{id}")
    public ResponseEntity<Dataset> updateDataset(@PathVariable String id, @RequestBody Dataset datasetDetails) {
        Optional<Dataset> dataset = datasetService.findById(id);
        if (dataset.isPresent()) {
            // actualizar propiedades del dataset
            Dataset existingDataset = dataset.get();
            existingDataset.setName(datasetDetails.getName());
            existingDataset.setDescription(datasetDetails.getDescription());
            // Actualizar el dataset en la base de datos
            return ResponseEntity.ok(datasetService.save(existingDataset));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataset(@PathVariable String id) {
        if (datasetService.findById(id).isPresent()) {
            datasetService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

