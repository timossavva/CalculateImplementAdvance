package indicators.objects;

import java.util.Date;


public class DeikthsHistoryEntry {
    private long id;
    private String name;
    private String type; // p.x. StoreName, BranchName
    private String value;
    private Date date;

    public DeikthsHistoryEntry() {
    }
}