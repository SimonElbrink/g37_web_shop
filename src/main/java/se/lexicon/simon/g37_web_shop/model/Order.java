package se.lexicon.simon.g37_web_shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",  strategy = "org.hibernate.id.UUIDGenerator")
    private String orderId;
    private LocalDateTime lastUpdate;
    private BigDecimal totalPrice;
//    OrderStatus orderStatus; // TODO
@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
@JoinColumn(name = "customer_id")
private Customer customer;


    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "order" //connection to the owner of the relationship
    )
    private Set<OrderItem> orderItems;


}
