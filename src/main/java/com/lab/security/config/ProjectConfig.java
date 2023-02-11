package com.lab.security.config;

import com.lab.security.config.filters.AuthenticationLoggingFilter;
import com.lab.security.config.filters.CsrfTokenLogger;
import com.lab.security.config.filters.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

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
        http.httpBasic(withDefaults());

        http.formLogin()
                .defaultSuccessUrl("/main", true);

        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeHttpRequests((authorize) ->
                authorize  // .requestMatchers(HttpMethod.POST, "/api/auth/signing").permitAll()
                        //.requestMatchers("/api/auth/**").permitAll()
                        //.anyRequest().authenticated()
                        .anyRequest().hasAnyAuthority("WRITE","READ")
        );

        return http.build();
    }
}
