package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.security.Role;
import com.example.travelguidewebapplication.security.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    Integer id;

    @NotBlank(message = "İstifadəçi soyadı boş ola bilməz")
    @Size(min = 2, max = 20, message = "İstifadəçi adı en az 2 en fazla 20 karakter olabilir")
    String firstname;

    @NotBlank(message = "İstifadəçi adı boş ola bilməz")
    @Size(min = 2, max = 20, message = "İstifadəçi adı en az 2 en fazla 20 karakter olabilir")
    String lastname;

    @NotBlank(message = "E-posta adresi boş ola bilməz")
    @Email(message = "Geçersiz e-posta adresi")
    String email;

    @Column(name = "password")
    String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
