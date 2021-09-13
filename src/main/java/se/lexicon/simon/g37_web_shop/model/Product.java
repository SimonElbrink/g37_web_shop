package se.lexicon.simon.g37_web_shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String productId;
    private String productName;
    private String description;
    private BigDecimal productPrice;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_category_id")
    )
    private Set<ProductCategory> categories;

    public Product() {
    }

    public Product(String productName, String description, BigDecimal productPrice) {
        this.productName = productName;
        this.description = description;
        this.productPrice = productPrice;
        categories = new HashSet<>();
    }

    //Convenience Method
    public void addProductCategory(ProductCategory productCategory){
        if (productCategory == null) throw new IllegalArgumentException("productCategory can not be null!");
        if (categories == null) categories = new HashSet<>();

        categories.add(productCategory);
        productCategory.getProductWithCategory().add(this);

    }
    //Convenience Method
    public void removeProductCategory(ProductCategory productCategory) {
        if (productCategory == null) throw new IllegalArgumentException("productCategory can not be null!");
        if (categories == null) categories = new HashSet<>();

        categories.remove(productCategory);
        productCategory.getProductWithCategory().remove(this);

    }

    /**
     * TODO
     * @param categories
     */
    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getProductPrice(), product.getProductPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getDescription(), getProductPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
