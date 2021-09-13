package se.lexicon.simon.g37_web_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.simon.g37_web_shop.data.dao.CustomerDao;
import se.lexicon.simon.g37_web_shop.data.repository.OrderItemRepository;
import se.lexicon.simon.g37_web_shop.data.repository.ProductRepository;
import se.lexicon.simon.g37_web_shop.model.*;

import java.math.BigDecimal;
import java.util.Collection;

@Component // Bean
public class MyCommandLineRunner implements CommandLineRunner {
    //New "main method"

    ProductRepository productRepo;
    CustomerDao customerDao;
    OrderItemRepository orderItemRepo;

    @Autowired // Constructor injection (Injecting bean)
    public MyCommandLineRunner(ProductRepository productRepo, CustomerDao customerDao, OrderItemRepository orderItemRepo) {
        this.productRepo = productRepo;
        this.customerDao = customerDao;
        this.orderItemRepo = orderItemRepo;
    }


    @Override
    public void run(String... args) throws Exception {


        Product product = new Product("Harry Potter 1",
                "This about a Stone and Harry Potter..",
                BigDecimal.valueOf(250));
        product = productRepo.save(product);

        Product product1 = new Product("Lord Of The Rings 1",
                "This about a Ring..",
                BigDecimal.valueOf(300));
        product1 = productRepo.save(product1);

        Collection<Product> products = productRepo.findAllByProductNameContainsIgnoreCase("Harry Potter 1");

        products.forEach(System.out::println);

        Address address = new Address(null,"Nygatan 1", "36073", "Växjö", "Sweden");
        AppUser appUser = new AppUser(null, "simonelbrink", "HeyThisIsHidden");

        Customer customer = new Customer(null,"Simon", "Elbrink", "simon@lexicon.se",appUser,address);

        customer = customerDao.persist(customer);



        OrderItem orderItem10 = new OrderItem(10, product, null);
        OrderItem orderItem5 = new OrderItem(5, product, null);
        OrderItem orderItemLOTR5 = new OrderItem(5, product1, null);

        orderItemRepo.save(orderItem5);
        orderItemRepo.save(orderItem10);
        orderItemRepo.save(orderItemLOTR5);

        System.out.println("OrderItemBy ProductID!");
        orderItemRepo.findOrderItemByProductId(product.getProductId())
                .forEach(System.out::println);





    }

}
