package notifications.objects;

import branches.objects.Branch;
import stores.objects.Store;

import java.util.Date;

public abstract class Notification {

    protected Store store;
    protected Branch branch;
    protected double value;
    protected Date date;
    protected boolean enable;
    protected String operator;

    public Notification(Store store, Branch branch, Date date, double value, boolean enable, String operator) {
        this.store = store;
        this.branch = branch;
        this.date = date;
        this.enable = enable;
        this.operator = operator;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public abstract boolean checkIfCompleted();

}
