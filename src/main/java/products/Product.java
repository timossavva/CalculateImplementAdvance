package products;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "product")
public class Product {
    private long id;
    private String name;
    private String code;
    private Double price;
//    private Set<BranchProduct> branchProducts;


    public Product() {
    }

    public Product(String name, String code, Double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

//    @OneToMany(mappedBy = "product")
//    public Set<BranchProduct> getBranchProducts() {
//        return branchProducts;
//    }

//    public void setBranchProducts(Set<BranchProduct> storeProducts) {
//        this.branchProducts = storeProducts;
//    }
}