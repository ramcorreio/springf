package club.colab4p.springf.repo;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import club.colab4p.springf.config.ApplicationConfigTest;
import club.colab4p.springf.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfigTest.class})
@Transactional
@Rollback(false)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;
	
	@Test
	public void findByLastName() {
		
		assertThat(repository, is(notNullValue()));
		List<Customer> rs1 = repository.findByLastName("Macedo");
		assertThat(rs1, is(empty()));
		
		Customer rodrigo = new Customer();
		rodrigo.setFirstName("Rodrigo");
		rodrigo.setLastName("Macedo");
		repository.saveAndFlush(rodrigo);
		
		Customer luciana = new Customer();
		luciana.setFirstName("Luciana");
		luciana.setLastName("Neri");
		repository.saveAndFlush(luciana);
		
		List<Customer> rs2 = repository.findByLastName("Macedo");
		assertThat(rs2, hasSize(1));
		
		List<Customer> rs3 = repository.findAll();
		assertThat(rs3, hasSize(2));
	}
}