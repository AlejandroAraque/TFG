package com.example.tfg.Controller;

import com.example.tfg.DTO.EnrichedAccessRequestDTO;
import com.example.tfg.DTO.RequestedAccessRequestDTO;
import com.example.tfg.Model.AccessRequest;
import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import com.example.tfg.Service.AccessRequestService;
import com.example.tfg.Service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AccessRequestController {

    @Autowired
    private AccessRequestService accessRequestService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DatasetService datasetService;

    @GetMapping
    public List<AccessRequest> getAllAccessRequests() {
        return accessRequestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessRequest> getAccessRequestById(@PathVariable String id) {
        Optional<AccessRequest> accessRequest = accessRequestService.findById(id);
        return accessRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/access-requests")
    public ResponseEntity<AccessRequest> createAccessRequest(@RequestBody AccessRequest accessRequest) {
        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Obtener el nombre de usuario del usuario autenticado
            String username = authentication.getName();
            System.out.println(username);
            // Buscar al usuario en la base de datos por el nombre de usuario
            Optional<Users> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent()) {
                Users user = userOptional.get();
                System.out.println(user.getId());
                // Asignar el ID del usuario autenticado como consumerId
                accessRequest.setConsumerId(user.getId());

                // Guardar la solicitud de acceso en la base de datos
                AccessRequest savedAccessRequest = accessRequestService.save(accessRequest);

                return ResponseEntity.ok(savedAccessRequest);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/access-requests/received")
    public List<EnrichedAccessRequestDTO> getReceivedAccessRequests() {
        String providerId = datasetService.getAuthenticatedProviderId();
        return datasetService.getEnrichedAccessRequestsForProvider(providerId);
    }

    @GetMapping("/access-requests/requested")
    public List<RequestedAccessRequestDTO> getRequestedAccessRequests() {
        String userId = datasetService.getAuthenticatedProviderId();
        return datasetService.getRequestedAccessRequestsForUser(userId);
    }




    @PutMapping("/{id}")
    public ResponseEntity<AccessRequest> updateAccessRequest(@PathVariable String id, @RequestBody AccessRequest accessRequestDetails) {
        Optional<AccessRequest> accessRequest = accessRequestService.findById(id);
        if (accessRequest.isPresent()) {
            accessRequestDetails.setId(id);
            return ResponseEntity.ok(accessRequestService.save(accessRequestDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/datasets/check-auth/{id}")
    public ResponseEntity<Map<String, Object>> checkAuthAndTerms(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Obtener el nombre de usuario del usuario autenticado
            String username = authentication.getName();

            // Buscar al usuario en la base de datos por el nombre de usuario
            Optional<Users> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                Users user = userOptional.get();

                // Aquí puedes agregar lógica para verificar si el usuario ya aceptó los términos para este dataset
                boolean termsAccepted = false; // Supón que tienes algún mecanismo para verificar esto

                Map<String, Object> response = new HashMap<>();
                response.put("authenticated", true);
                response.put("termsAccepted", termsAccepted);
                response.put("username", user.getUsername());
                return ResponseEntity.ok(response);
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", false);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessRequest(@PathVariable String id) {
        if (accessRequestService.findById(id).isPresent()) {
            accessRequestService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
