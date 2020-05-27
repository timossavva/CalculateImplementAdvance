import users.LoginForm;

public class Main {

    public static void main(String[] args) {
        new LoginForm();
    }
    //test
}


/*

        BranchList branchList = new BranchList(sessionFactory);
        ArrayList<Branch> allBranches = branchList.getAll();
        for (Branch branch : allBranches) {
            System.out.println("Branch -> {id: " + branch.getId() + ", name: " + branch.getName() + ", codeName: " + branch.getCodeName() + "}");
            for (BranchProduct branchProduct : branch.getStoreProducts()) {
                Product product = branchProduct.getProduct();
                System.out.println("    Product -> {id: " + product.getId() + ", name: " + product.getName() + ", code: " + product.getCode() + ", price: " + product.getPrice() + ", quantity: " + branchProduct.getQuantity() + "}");
            }
            System.out.println();
        }

        ProductList productList = new ProductList(sessionFactory);
        ArrayList<Product> allProducts = productList.getAll();

        Branch branch1 = allBranches.get(0);
        branch1.addProduct(sessionFactory, allProducts.get(0), 400);

        session.getTransaction().commit();
        session.close();




        Store store1 = new Store("Masoutis", "32165");
        store1.setId(1);

        Set<Branch> branches = new HashSet<>();
        branches.add(new Branch("Masoutis A", "65468", store1));
        branches.add(new Branch("Masoutis B", "65469", store1));
        branches.add(new Branch("Masoutis C", "65470", store1));

        store1.setBranches(branches);
        session.save(store1);

        BranchList branchList = new BranchList(sessionFactory);
        ArrayList<Branch> allBranches = branchList.getAll();
        for (Branch branch : allBranches) {
            System.out.println("Branch -> {id: " + branch.getId() + ", name: " + branch.getName() + ", codeName: " + branch.getCodeName() + "}");
        }

        StoreList storeList = new StoreList(sessionFactory);
        ArrayList<Store> allStores = storeList.getAll();
        for (Store store : allStores) {
            System.out.println(store.getId());
            System.out.println(store.getName());
            System.out.println(store.getCodeName());
            for (Branch branch : store.getBranches()) {
                System.out.println("Branch -> {id: " + branch.getId() + ", name: " + branch.getName() + ", codeName: " + branch.getCodeName() + "}");
            }
        }
*/
