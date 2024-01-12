package org.capisoft.securitybackend;

import org.capisoft.securitybackend.entities.Role;
import org.capisoft.securitybackend.repositories.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SecurityBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(SecurityBackendApplication.class, args);

        RoleRepository roleRepository = configurableApplicationContext.getBean(RoleRepository.class);
        if (roleRepository.count() == 0) {
            List<Role> roles = List.of(
                    Role.builder().name("SUPER-ADMIN").build(),
                    Role.builder().name("ADMIN").build(),
                    Role.builder().name("SECRETARY").build()
            );
            roleRepository.saveAll(roles);
        }
    }

}
