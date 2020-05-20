package primary_accounts;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "primary_account")
public class PrimaryAccount {
    private long id;
    private String name;
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
}
