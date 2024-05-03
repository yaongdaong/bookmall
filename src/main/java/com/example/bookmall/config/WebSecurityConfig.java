package com.example.bookmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // @Autowired
    // private UserService userDetailsService;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // .requestMatchers("/json/**").authenticated()
                        .requestMatchers("/", "books", "books/add", "/join", "/home", "/images/**", "/jquery.min.js").permitAll()
                        .requestMatchers("/user/my_page").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/my-page")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    //public UserDetailsService userDetailsService(){
    //    UserDetails user = User.withDefaultPasswordEncoder()
    //            .username("user")
    //            .password("password")
    //            .roles("USER")
    //            .build();
    //
    //    UserDetails admin = User.withDefaultPasswordEncoder()
    //            .username("admin")
    //            .password("password")
    //            .roles("ADMIN")
    //            .build();
    //    return new InMemoryUserDetailsManager(user, admin);
    //}
}
