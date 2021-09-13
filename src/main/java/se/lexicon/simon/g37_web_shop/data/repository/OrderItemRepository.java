package se.lexicon.simon.g37_web_shop.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.simon.g37_web_shop.model.OrderItem;

import java.util.Collection;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {

    //JPQL
    @Query("SELECT item FROM OrderItem item WHERE item.product.productId = :id")
    Collection<OrderItem> findOrderItemByProductId(@Param("id") String productId);

}
