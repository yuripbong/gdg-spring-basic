package gdgStudy.gdgSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/mainPage", "/login", "signUp").permitAll()
                        .requestMatchers("/adminPage").hasRole("ADMIN")
                        .requestMatchers("/myPage/**").hasAnyRole("ADMIN","USER")
                );

        return httpSecurity.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((csrf) -> csrf.disable())
                        .authorizeHttpRequests((auth) -> auth
                                .requestMatchers("/mainPage").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/adminPage").hasRole("ADMIN")
                                .requestMatchers("/myPage/**").hasAnyRole("ADMIN", "USER")
                        )
                        .formLogin((form) -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .permitAll());

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
