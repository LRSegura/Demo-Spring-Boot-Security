package com.lab.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectConfig {

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    //Only for test
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .defaultSuccessUrl("/main", true);
        http.csrf().disable().authorizeHttpRequests((authorize) ->
                authorize  // .requestMatchers(HttpMethod.POST, "/api/auth/signing").permitAll()
                        //.requestMatchers("/api/auth/**").permitAll()
                        //.anyRequest().authenticated()
                        .anyRequest().hasAnyAuthority("WRITE","READ")
        ).httpBasic(withDefaults());
        return http.build();
    }
}
