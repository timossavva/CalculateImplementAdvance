package notifications.objects;

import java.util.Date;

public abstract class Notification {

    protected long id;
    protected String storeName;
    protected String branchId;
    protected float value;
    protected Date date;


    public abstract boolean checkIfCompleted();

}
