package users;

public class AccountType {
    private String name;
    private String codeName;

    public static String[] getAccountTypes() {
        return new String[]{"Διαχειριστής", "Λογιστής", "Γενικός Διευθυντής", "CFO", "Πωλητής", "Αποθηκάριος"};
    }

//    public static LinkedHashMap<String, String> getAccountTypes() {
//        LinkedHashMap<String, String> accountTypes = new LinkedHashMap<>();
//        accountTypes.put("admin", "Διαχειριστής");
//        accountTypes.put("accountant", "Λογιστής");
//        accountTypes.put("manager", "Γενικός Διευθυντής");
//        accountTypes.put("cfo", "CFO");
//        accountTypes.put("storekeeper", "Πωλητής");
//        accountTypes.put("seller", "Αποθηκάριος");
//        return accountTypes;
//    }
//
//    public static String[] getAccountTypeNames() {
//        LinkedHashMap<String, String> accountTypes = getAccountTypes();
//        return new ArrayList<>(accountTypes.values()).toArray(new String[0]);
//    }
//
//    public static String[] getAccountTypeCodeNames() {
//        LinkedHashMap<String, String> accountTypes = getAccountTypes();
//        return new ArrayList<>(accountTypes.keySet()).toArray(new String[0]);
//    }

}
