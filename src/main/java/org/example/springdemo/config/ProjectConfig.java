package org.example.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {
    // Переопределяем UserDetailsService, который используется по умолчанию в Spring Security
    // @Bean предписывает Spring добавить экземпляр, возвращаемый методом, в контекст Spring
    @Bean
    UserDetailsService userDetailsService() {
        // Создаем пользователя с заданным именем пользователя, паролем и списком полномочий.
        // Добавляем пользователя для управления UserDetailsService.
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
