package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true)
public class ResourceServerConfig {

    @Bean
    @Order(0)
    public SecurityFilterChain publicEndpoints(HttpSecurity http, JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
        http
                .securityMatcher("/academic-degrees/**", "/academic-titles/**", "/education-subjects/**",
                        "/achievements/**", "/outstanding-people/**", "/job-titles/**", "/person-jobs/**",
                        "/users/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,
                                "/academic-degrees",
                                "/academic-degrees/{id}",
                                "/academic-titles",
                                "/academic-titles/{id}",
                                "/education-subjects",
                                "/education-subjects/{id}",
                                "/achievements",
                                "/achievements/{id}",
                                "/outstanding-people/{personId}/achievements",
                                "/outstanding-people",
                                "/outstanding-people/{id}",
                                "/outstanding-people/search/**",
                                "/job-titles",
                                "/job-titles/{id}",
                                "/person-jobs",
                                "/person-jobs/{id}",
                                "/outstanding-people/{personId}/jobs",
                                "/outstanding-people/{personId}/jobs/current")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/users",
                                "/users/{id}",
                                "/users/{id}",
                                "/academic-degrees/deleted",
                                "/academic-degrees/deleted/{id}",
                                "/academic-titles/deleted",
                                "/academic-titles/deleted/{id}",
                                "/education-subjects/deleted",
                                "/education-subjects/deleted/{id}",
                                "/achievements/deleted",
                                "/achievements/deleted/{id}"
                                , "/outstanding-people/deleted",
                                "/outstanding-people/deleted/{id}",
                                "/job-titles/deleted",
                                "/job-titles/deleted/{id}",
                                "/person-jobs/deleted",
                                "/person-jobs/deleted/{id}")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtConverter;
    }
}