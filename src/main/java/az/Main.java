package az;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(scanBasePackages = "az.example.online.shopping")
@ComponentScan(basePackages = "az.example.online.shopping")
@EnableSpringDataWebSupport(pageSerializationMode =
        EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@EnableJpaRepositories(basePackages = "az.example.online.shopping.infrastructure.dataaccess.repository")
@EntityScan(basePackages = "az.example.online.shopping.infrastructure.dataaccess.entity")

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
