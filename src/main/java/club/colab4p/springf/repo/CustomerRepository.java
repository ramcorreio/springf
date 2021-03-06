package club.colab4p.springf.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import club.colab4p.springf.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "select c from Customer c where c.lastName like %:lastName")
    public List<Customer> findByLastName(@Param("lastName") String lastName);
}