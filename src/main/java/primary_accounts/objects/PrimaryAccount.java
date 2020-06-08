package primary_accounts.objects;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "primary_account")
public class PrimaryAccount {
    private long id;
    private String name;
    private boolean isYearly;
    private boolean isDynamic;
    private Set<PrimaryAccountBranch> primaryAccountBranchSet;

    public PrimaryAccount() {
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "primaryAccount")
    public Set<PrimaryAccountBranch> getPrimaryAccountBranchSet() {
        return primaryAccountBranchSet;
    }

    public void setPrimaryAccountBranchSet(Set<PrimaryAccountBranch> primaryAccountBranchSet) {
        this.primaryAccountBranchSet = primaryAccountBranchSet;
    }

    @org.hibernate.annotations.Type(type = "true_false")
    @NotNull
    @Column(name = "is_yearly")
    public boolean isYearly() {
        return isYearly;
    }

    public void setYearly(boolean yearly) {
        isYearly = yearly;
    }

    @org.hibernate.annotations.Type(type = "true_false")
    @NotNull
    @Column(name = "is_dynamic")
    public boolean isDynamic() {
        return isDynamic;
    }

    public void setDynamic(boolean dynamic) {
        isDynamic = dynamic;
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        } else {
            PrimaryAccount otherPrimaryAccount = (PrimaryAccount) otherObject;
            return id == otherPrimaryAccount.id;
        }
    }
}
