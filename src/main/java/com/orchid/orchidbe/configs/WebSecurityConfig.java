package com.orchid.orchidbe.configs;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import com.orchid.orchidbe.filters.JwtTokenFilter;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@EnableWebMvc
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(auth -> auth
                        // Authentication endpoints
                        .requestMatchers(
                                String.format("%s/auth/login", apiPrefix)
                        ).permitAll()

                        // Public API endpoints accessible by ROLE_USER
                        .requestMatchers(GET,
                                String.format("%s/orchids/**", apiPrefix),
                                String.format("%s/categories/**", apiPrefix)
                        ).hasAuthority("ROLE_ADMIN")

                        // Admin-only endpoints
                        .requestMatchers(POST,
                                String.format("%s/orchids/**", apiPrefix),
                                String.format("%s/categories/**", apiPrefix)
                        ).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(PUT,
                                String.format("%s/orchids/**", apiPrefix),
                                String.format("%s/categories/**", apiPrefix)
                        ).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(DELETE,
                                String.format("%s/orchids/**", apiPrefix),
                                String.format("%s/categories/**", apiPrefix)
                        ).hasAuthority("ROLE_ADMIN")

                        // Swagger and other public endpoints
                        .requestMatchers(
                                "/graphiql", "/graphql", "/error",
                                "/v3/api-docs/**", "/v3/api-docs.yaml", "/v3/api-docs/swagger-config",
                                "/swagger-ui/**", "/swagger-ui.html",
                                apiPrefix + "/swagger-ui/**",
                                apiPrefix + "/swagger-ui.html",
                                apiPrefix + "/api-docs/**"
                        ).permitAll()

                        .anyRequest().authenticated())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:4000",
            "http://localhost:5173"));

        // Allow common HTTP methods
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD"));

        // Allow all headers
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "X-Requested-With",
            "Accept",
            "Origin",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"));

        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList(
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials",
            "Authorization"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}