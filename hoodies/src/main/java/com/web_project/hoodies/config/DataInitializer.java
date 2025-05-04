package com.web_project.hoodies.config;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web_project.hoodies.model.Product;
import com.web_project.hoodies.model.Role;
import com.web_project.hoodies.repository.ProductRepository;
import com.web_project.hoodies.repository.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    @Transactional
    public CommandLineRunner loadData(RoleRepository roleRepository, ProductRepository productRepository) {
        return args -> {
            // Initialize roles
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                roleRepository.save(new Role(null, "ROLE_USER", null));
            }
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepository.save(new Role(null, "ROLE_ADMIN", null));
            }

            // Initialize products
            if (productRepository.count() == 0) {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {};
                try (InputStream inputStream = new ClassPathResource("hoodies_data.json").getInputStream()) {
                    List<Product> products = mapper.readValue(inputStream, typeReference);
                    productRepository.saveAll(products);
                    logger.info("Products saved to the database.");
                } catch (Exception e) {
                    logger.error("Unable to save products: {}", e.getMessage(), e);
                }
            }
        };
    }
}
