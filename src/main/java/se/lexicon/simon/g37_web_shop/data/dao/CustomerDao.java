package se.lexicon.simon.g37_web_shop.data.dao;

import se.lexicon.simon.g37_web_shop.model.Customer;

import java.util.Collection;

public interface CustomerDao {

    //CRUD

    Customer persist(Customer customer);

    Customer findById(String id);
    Collection<Customer> findAll();
    Collection<Customer> findByNameContainsIgnoreCase(String name);
    Customer findByEmail(String email);

    Customer update(Customer customer);

    void remove(Customer customer);

}
