package com.example.tfg.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class Users implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @NotBlank
    private String password;

    @Indexed(unique = true)
    private String email;

    private Role role;

    // Constructor adicional que toma username, password y email
    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.USER; // Asignar un rol predeterminado, si es necesario
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asegurarse de que el rol no sea nulo
        if (role == null) {
            role = Role.USER; // Asignar un rol predeterminado si es necesario
        }
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
