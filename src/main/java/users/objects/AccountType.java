package users.objects;

public class AccountType {
    private String name;
    private String codeName;

    public static String[] getAccountTypes() {
        return new String[]{"Διαχειριστής", "Λογιστής", "Γενικός Διεθυντής", "CFO", "Πωλητής", "Αποθηκάριος"};
    }


}
