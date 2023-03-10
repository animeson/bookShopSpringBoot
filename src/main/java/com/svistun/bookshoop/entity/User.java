package com.svistun.bookshoop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @Email(message = "E-mail should not be empty")
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "New password is mandatory")
    @Column(nullable = false,length = 100)
    private String password;
    private LocalDateTime registerDate;
    @Column(length = 50)
    private String lastName;
    @Column(length = 50)
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
    @OneToOne(/*mappedBy = "user",*/
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "userImage_id")
    private UserImage userImage;

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
