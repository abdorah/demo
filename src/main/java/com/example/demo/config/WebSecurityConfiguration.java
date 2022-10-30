package com.example.demo.config;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    DataSource dataSource;

    protected DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage());
                        })
                .and();

        http.authorizeRequests()
                // .antMatchers("/api/public/**").permitAll()
                // .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
                // .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
                // .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
                // .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
                .anyRequest().authenticated();
        http.formLogin().permitAll().and().logout().permitAll();
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jdbcUserDetailsManager(dataSource)).passwordEncoder(passwordEncoder()).and()
                .build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
        String authsByUserQuery = "select username, authority from authorities where username = ?";
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return userDetailsManager;
    }

    /*
     * @Bean
     * public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
     * JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
     * jdbcUserDetailsManager.setDataSource(dataSource);
     * return jdbcUserDetailsManager;
     * }
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
