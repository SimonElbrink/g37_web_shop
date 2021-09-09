package se.lexicon.simon.g37_web_shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",  strategy = "org.hibernate.id.UUIDGenerator")
    private String orderItemId;

    private Integer amount;
    private BigDecimal itemPrice;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", table = "order_item")
   private Product product;


    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", table = "order_item") // Owning the relationship
    private Order order;

}
