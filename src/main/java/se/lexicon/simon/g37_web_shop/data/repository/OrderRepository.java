package se.lexicon.simon.g37_web_shop.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.simon.g37_web_shop.model.Order;

import java.util.Collection;

public interface OrderRepository extends CrudRepository<Order, String> {


    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems AS item WHERE UPPER(item.product.productName) = UPPER(:name)")
    Collection<Order> findOrderByProductName(@Param("name") String name);

//    Collection<Order> findOrderByOrderItems_Product_ProductName(String name);

}
