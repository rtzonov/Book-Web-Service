package com.fitr.bntu.bookwebservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.*;

import javax.sql.DataSource;


@Profile("Main")
@EnableJpaRepositories(basePackages = "com.fitr.bntu.bookwebservice")
@ComponentScan(basePackages = "com.fitr.bntu.bookwebservice")
@Configuration
public class ServiceUtilConfig implements WebMvcConfigurer {
    private static final String PACKAGES_TO_SCAN = "com.fitr.bntu.bookwebservice";

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public LocalSessionFactoryBean entityManagerFactory(DataSource dataSource){
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(PACKAGES_TO_SCAN);
        return factory;
    }

    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        registry.addViewController("/login")
                .setViewName("login");
        registry.addViewController("/registration")
                .setViewName("registration");
        registry.addViewController("/cart")
                .setViewName("cart");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/img/");
    }
}
