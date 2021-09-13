package se.lexicon.simon.g37_web_shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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

    public OrderItem() {
    }

    public OrderItem(Integer amount, Product product, Order order) {
        this.amount = amount;
        this.product = product;
        this.order = order;
        calcPrice();
    }

    private void calcPrice(){

        itemPrice = product.getProductPrice().multiply(BigDecimal.valueOf(amount));

    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getAmount(), orderItem.getAmount()) && Objects.equals(getItemPrice(), orderItem.getItemPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getItemPrice());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId='" + orderItemId + '\'' +
                ", amount=" + amount +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
