package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Email(message = "E-mail should not be empty")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false, length = 100)
    private String password;

    private LocalDateTime registerDate;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    private Boolean gender;

    @Column(length = 100)
    private String location;

    @Column(length = 25)
    private String phone;

    @Column(length = 25)
    private Integer zipCode;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userImage_id")
    private UserImage userImage;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Token> tokens;
    private Boolean isVerified;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "settingsNotification_id")
    @JsonIgnore
    private SettingsNotification settingsNotification;
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
