package xyz.zzyitj.ils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.zzyitj.ils.service.UserRoleService;
import xyz.zzyitj.ils.service.impl.UserRoleServiceImpl;

@SpringBootApplication
public class IlsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IlsApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRoleService userRoleService) {
        return (args -> {
            userRoleService.getUserRolesById(UserRoleServiceImpl.DEFAULT_USER_ID, true);
        });
    }
}
