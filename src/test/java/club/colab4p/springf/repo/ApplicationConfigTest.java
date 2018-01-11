package club.colab4p.springf.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import club.colab4p.springf.repo.ApplicationConfig;

@Configuration
@Import(ApplicationConfig.class)
@PropertySource("classpath:springf.test.properties")
public class ApplicationConfigTest {

}
