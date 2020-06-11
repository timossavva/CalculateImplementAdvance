package notifications.objects;

import branches.objects.Branch;
import primary_accounts_and_indicators.indicators.Indicator;
import primary_accounts_and_indicators.primary_accounts.PrimaryAccount;
import stores.objects.Store;

public abstract class Notification {

    protected Store store;
    protected Branch branch;
    protected double value;
    protected String date;
    protected boolean enable;
    protected String operator;
    protected Indicator indicator;
    protected PrimaryAccount primaryAccount;


    public Notification(Store store, Branch branch, String date, double value, boolean enable, String operator, Indicator indicator, PrimaryAccount primaryAccount) {
        this.store = store;
        this.branch = branch;
        this.value = value;
        this.date = date;
        this.enable = enable;
        this.operator = operator;
        this.indicator = indicator;
        this.primaryAccount = primaryAccount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public PrimaryAccount getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }
}
