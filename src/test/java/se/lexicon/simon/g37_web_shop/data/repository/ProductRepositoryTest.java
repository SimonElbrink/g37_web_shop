package se.lexicon.simon.g37_web_shop.data.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.simon.g37_web_shop.model.Product;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository testObject;

//    @Autowired
//    private TestEntityManager testEntityManager; // Can be used for more control.

    List<Product> productList = Arrays.asList(
            new Product("Harry Potter 1", "Staring of Harry Potter.", BigDecimal.valueOf(150)),
            new Product("Harry Potter 2", "Harry Potter Book.", BigDecimal.valueOf(150)),
            new Product("Lord Of The Ring 1", "Staring of the story of the Ring", BigDecimal.TEN),
            new Product("Lord Of The Ring 2", "Continue of the story of the Ring", BigDecimal.TEN),
            new Product("Lord Of The Ring 3", "End of the story of the Ring", BigDecimal.TEN),
            new Product("Ztockholm", "Apocalyptic story in Stockholm", BigDecimal.TEN),
            new Product("Hercule", "Drama!", BigDecimal.valueOf(199))
    );



    @BeforeEach
    void setUp() {

        testObject.saveAll(productList);
    }


    @Test
    void findAllByProductNameIgnoreCase() {

        // Arrange
        String productName = "HARRY";
        Collection<Product> foundProduct = new HashSet<>();

        // Act
        foundProduct = testObject.findAllByProductNameContainsIgnoreCase(productName);


        // Assert
        assertFalse(foundProduct.isEmpty());
        assertEquals(2, foundProduct.size());
        assertTrue(foundProduct.contains(productList.get(0)));
        assertTrue(foundProduct.contains(productList.get(1)));

    }
}