package se.lexicon.simon.g37_web_shop.data.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.simon.g37_web_shop.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class CustomerDaoJPAImpl implements  CustomerDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Customer persist(Customer customer) {
       entityManager.persist(customer);
        return customer;
    }

    @Override
    public Customer findById(String id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Collection<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c",Customer.class).getResultList();
    }

    @Override
    public Collection<Customer> findByNameContainsIgnoreCase(String name) {

        return entityManager.createQuery(
                "SELECT c FROM Customer c WHERE UPPER(c.firstName) LIKE UPPER(CONCAT('%',:name,'%')) " +
                        "OR " +
                        "UPPER(c.lastName) LIKE UPPER(CONCAT('%', :name, '%')) ", Customer.class)
                .setParameter("name", name)
                .getResultList();

    }

    @Override
    public Customer findByEmail(String email) {
        return entityManager.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst().orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public void remove(Customer customer) {
        entityManager.remove(customer);
    }
}
