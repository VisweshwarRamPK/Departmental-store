package com.viswa.DepartmentalStoreApplication.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityFilter {

    private final AuthenticationProvider authenticationProvider;

    private final  JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityFilter(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionMangConfig->sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(authConfig->{
                    // Whitelist endpoints
                    authConfig.requestMatchers(AUTH_WHITELIST).permitAll();

                    //  LOGIN OR REGISTER USER
                    authConfig.requestMatchers(HttpMethod.POST,"auth/registerAdmin").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST,"auth/authenticate","auth/register").permitAll();






                    // Any other request requires authentication
                    authConfig.anyRequest().authenticated();

                });
        return http.build();
    }
    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/mail/send",
    };


}