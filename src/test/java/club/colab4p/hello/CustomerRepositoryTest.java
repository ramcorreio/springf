package club.colab4p.hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import club.colab4p.hello.ApplicationConfig;
import club.colab4p.hello.CustomerRepository;

//import club.colab4p.hello.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = ApplicationConfig.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;
	
	@Test
	public void findByLastName() {
		
		Assert.assertNotNull(repository);
		Assert.fail("Não implementado...");
		
	}
}