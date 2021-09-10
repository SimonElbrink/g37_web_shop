package se.lexicon.simon.g37_web_shop.data.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.simon.g37_web_shop.model.Address;
import se.lexicon.simon.g37_web_shop.model.AppUser;
import se.lexicon.simon.g37_web_shop.model.Customer;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
class CustomerDaoJPAImplTest {

    Customer testObject;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    void setUp() {

        Address address = new Address(null,"Nygatan 1", "36073", "Växjö", "Sweden");
        AppUser appUser = new AppUser(null, "testtestsson", "HeyThisIsHidden");

        //Not needed if i have a cascade
//        entityManager.persist(address);
//        entityManager.persist(appUser);

        testObject = new Customer(null,"Test", "Testsson", "test@test.com",appUser,address);

        entityManager.persist(testObject);
    }

    @Test
    void findByNameContainsIgnoreCase() {

        // Arrange
        Collection<Customer> found = null;
        // Act

        found = customerDao.findByNameContainsIgnoreCase("tEsT");

        // Assert

        assertTrue(found.contains(testObject));

//        System.out.println(testObject.getShippingAddress().getAddressId());
//        System.out.println(testObject.getUserCredentials().getUserId());


    }
}