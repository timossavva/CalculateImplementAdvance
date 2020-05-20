package branches;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import primary_accounts.PrimaryAccountBranch;
import products.BranchProduct;
import products.Product;
import stores.Store;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "branch")
public class Branch {
    private long id;
    private String name;
    private String codeName;
    private String state;
    private String city;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private Store store;
    private Set<BranchProduct> branchProducts;
//    private Set<PrimaryAccountBranch> primaryAccountBranchSet;

    public Branch() {
    }

    public Branch(String name, String codeName, Store store) {
        this.name = name;
        this.codeName = codeName;
        this.store = store;
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

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToOne
    @JoinColumn(name = "store_id")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "branch")
    public Set<BranchProduct> getStoreProducts() {
        return branchProducts;
    }

    public void setStoreProducts(Set<BranchProduct> storeProducts) {
        this.branchProducts = storeProducts;
    }
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "branch")
//    public Set<PrimaryAccountBranch> getPrimaryAccountBranchSet() {
//        return primaryAccountBranchSet;
//    }
//
//    public void setPrimaryAccountBranchSet(Set<PrimaryAccountBranch> primaryAccountBranchSet) {
//        this.primaryAccountBranchSet = primaryAccountBranchSet;
//    }


    public void addProduct(SessionFactory sessionFactory, Product product, int quantity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        BranchProduct branchProduct = new BranchProduct();
        branchProduct.setBranch(this);
        branchProduct.setProduct(product);
        branchProduct.setQuantity(quantity);

        session.save(branchProduct);

        session.getTransaction().commit();
        session.close();
    }
}