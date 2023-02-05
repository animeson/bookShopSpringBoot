package com.svistun.bookshoop.security;

import com.svistun.bookshoop.filter.PersonAuthenticationFilter;
import com.svistun.bookshoop.filter.PersonAuthorizationFilter;
import com.svistun.bookshoop.service.user.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final PasswordEncoder encoder;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
                          AuthenticationManagerBuilder auth,
                          PersonServiceImpl userService) throws Exception {
        this.authenticationConfiguration = authenticationConfiguration;
        this.encoder = new BCryptPasswordEncoder();

        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        PersonAuthenticationFilter personAuthenticationFilter =
                new PersonAuthenticationFilter(authenticationConfiguration.getAuthenticationManager());

        personAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests((auth) -> {
                            try {
                                auth
                                        .antMatchers("/api/v1/login/**", "/api/v1/register/**", "/api/v1/token/refresh/**")
                                        .permitAll()
                                        .and()
                                        .authorizeRequests()
                                        .antMatchers("api/v1/user/**", "/api/v1/post/**")
                                        .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                                        .anyRequest()
                                        .authenticated()
                                        .and()
                                        .cors()
                                        .and()
                                        .addFilter(personAuthenticationFilter)
                                        .addFilterBefore(new PersonAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return encoder;
    }

}



