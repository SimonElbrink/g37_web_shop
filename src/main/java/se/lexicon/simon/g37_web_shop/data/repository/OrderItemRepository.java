package se.lexicon.simon.g37_web_shop.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.simon.g37_web_shop.model.OrderItem;

import java.util.Collection;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {

    //These Methods will do the same thing. One with Query that you write.

    // You can write it yourself with a JPQL statement
    @Query("SELECT item FROM OrderItem item WHERE item.product.productId = :id")
    Collection<OrderItem> findOrderItemByProductId(@Param("id") String productId);

//    // Derived Query - You can name a method a specific way and the framework might figure it out.
//    //Makes use of the field names in class searched in. Product_id is the field name of id in product.
    Collection<OrderItem> findOrderItemByProduct_ProductId(@Param("id") String productId);

}
