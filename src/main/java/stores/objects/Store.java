package stores.objects;

import branches.objects.Branch;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "store")
public class Store {
    private long id;
    private String name;
    private String codeName;
    private String state;
    private String city;
    private String address;
    private String postalCode;
    private String phoneNumber;
    //    private User cfo;
    private Set<Branch> branches;

    public Store() {
    }

    public Store(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "store", cascade = CascadeType.ALL)
    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public void addBranch(Branch branch) {
        System.out.println(branch);
        branches.add(branch);
        branch.setStore(this);
    }

    public void removeBranch(Branch branch) {
        branches.remove(branch);
        branch.setStore(null);
    }
}