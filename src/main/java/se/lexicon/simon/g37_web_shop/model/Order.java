package se.lexicon.simon.g37_web_shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
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


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(getLastUpdate(), order.getLastUpdate()) && Objects.equals(getTotalPrice(), order.getTotalPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastUpdate(), getTotalPrice());
    }
}
