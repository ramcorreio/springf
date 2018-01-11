package club.colab4p.springf.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import club.colab4p.springf.repo.CustomerRepository;


public class ApplicationConfigContextTest {
	
	@Test
	public void bootstrapAppFromJavaConfig() {

		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigTest.class);
		assertThat(context, is(notNullValue()));
		assertThat(context.getBean(CustomerRepository.class), is(notNullValue()));
	}
}
