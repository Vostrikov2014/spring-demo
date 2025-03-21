package org.example.springdemo.config;

import lombok.RequiredArgsConstructor;
import org.example.springdemo.security.AuthenticationLoggingFilter;
import org.example.springdemo.security.RequestValidationFilter;
import org.example.springdemo.security.StaticKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {

    private final StaticKeyAuthenticationFilter filter;

    public ProjectConfig(StaticKeyAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );*/

        // Настройка пользовательского фильтра авторизации
        // добавление фильтра перед существующим фильтром в цепочке (BasicAuthenticationFilter)
        /*http.addFilterBefore(
                        new RequestValidationFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAfter(
                        new AuthenticationLoggingFilter(),
                        BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());*/

        // Заменить встроенный фильтр BasicAuthenticationFilter, кастомным фильтром
        http.addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }

// Переопределяем UserDetailsService, который используется по умолчанию в Spring Security
// @Bean предписывает Spring добавить экземпляр, возвращаемый методом, в контекст Spring
    /*@Bean
    UserDetailsService userDetailsService() {
        // Создаем пользователя с заданным именем пользователя, паролем и списком полномочий.
        // Добавляем пользователя для управления UserDetailsService.
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // По умолчанию используются
        //return new JdbcUserDetailsManager(dataSource);

        //Переопределение запросов в JdbcUserDetailsManager
        String usersByUsernameQuery =
                "select username, password, enabled from users where username = ?";
        String authsByUserQuery =
                "select username, authority from spring.authorities where username = ?";
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return userDetailsManager;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
