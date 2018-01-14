package club.colab4p.springf.repo;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		
		long totalCustomersBefore = repository.count();
		Customer rodrigo = new Customer();
		rodrigo.setFirstName("Rodrigo");
		rodrigo.setLastName("Macedo");
		repository.save(rodrigo);
		
		Customer luciana = new Customer();
		luciana.setFirstName("Luciana");
		luciana.setLastName("Neri");
		repository.save(luciana);
		
		List<Customer> rs2 = repository.findByLastName("Macedo");
		assertThat(rs2, hasSize(1));
		
		List<Customer> rs3 = repository.findAll();
		assertThat(rs3, hasSize(2 + Long.valueOf(totalCustomersBefore).intValue()));
	}
	
	@Test
	public void count() {
		
		Random r = new Random();
		long totalCustomersBefore = repository.count();
		long totalCustomers = r.nextInt(1000);
		
		List<Customer> customers = new ArrayList<>();
		for (int i = 0; i < totalCustomers; i++) {
			
			int index = i + 1;
			Customer c = new Customer();
			c.setFirstName("First " + index);
			c.setLastName("Last " + index);
			customers.add(c);
		}
		
		repository.saveAll(customers);
		assertThat(totalCustomers, is(equalTo(repository.count() + Long.valueOf(totalCustomersBefore).intValue())));
	}
}