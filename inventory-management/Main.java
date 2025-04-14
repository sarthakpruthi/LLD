import entity.Product;
import entity.User;
import entity.Warehouse;
import enums.Category;
import manager.OrderManager;
import manager.UserManager;
import manager.WarehouseManager;
import service.NearestWarehouseSelection;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        WarehouseManager warehouseManager = new WarehouseManager(new NearestWarehouseSelection());
        OrderManager orderManager = new OrderManager(userManager);

        //date creation
        createUsers(userManager);
        createWarehouses(warehouseManager);
        addProductsInWarehouse(warehouseManager.getWarehouse());

        prepareCart(userManager);
        orderManager.placeOrder(1, warehouseManager.getWarehouse());
    }

    private static void prepareCart(UserManager userManager) {
        userManager.addToCart(1, 1, 3);
        userManager.addToCart(1, 1, 1);
        userManager.addToCart(1, 2, 2);
    }

    private static void createWarehouses(WarehouseManager warehouseManager) {
        Warehouse w1 = new Warehouse(1,"delhi");
        Warehouse w2 = new Warehouse(2,"rohtak");
        Warehouse w3 = new Warehouse(3,"pune");
        warehouseManager.addWarehouses(w1);
        warehouseManager.addWarehouses(w2);
        warehouseManager.addWarehouses(w3);
    }

    private static void addProductsInWarehouse(Warehouse w1) {
        w1.addProduct(new Product(1, "cake" , Category.BAKERY, 100, 40));
        w1.addProduct(new Product(2, "real juice" , Category.DRINKS, 2, 30));
    }

    private static void createUsers(UserManager userManager) {
        userManager.addUser(new User(1, "sarthak"));
        userManager.addUser(new User(2, "amit"));
        userManager.addUser(new User(3, "bbr"));
    }
}