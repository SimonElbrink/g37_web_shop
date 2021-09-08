package se.lexicon.simon.g37_web_shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
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

    //Convenience Method - Naive
    public void addProductCategory(ProductCategory productCategory){
        categories.add(productCategory);
        productCategory.getProductWithCategory().add(this);

    }
    //Convenience Method - Naive
    public void removeProductCategory(ProductCategory productCategory){
        categories.remove(productCategory);
        productCategory.getProductWithCategory().remove(this);

    }





}
