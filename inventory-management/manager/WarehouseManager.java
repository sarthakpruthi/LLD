package manager;

import entity.Warehouse;
import service.WarehouseSelection;

import java.util.ArrayList;
import java.util.List;

public class WarehouseManager {
    List<Warehouse> warehouses;
    WarehouseSelection warehouseSelection;

    public WarehouseManager(WarehouseSelection warehouseSelection) {
        this.warehouses = new ArrayList<>();
        this.warehouseSelection = warehouseSelection;
    }

    public Warehouse getWarehouse(){
       return warehouseSelection.getWarehouse(warehouses);
    }

    public void addWarehouses(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    public Warehouse getWarehouseById(int id){
        return warehouses.stream().filter(warehouse -> warehouse.getId() == id).findFirst().orElse(null);
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public WarehouseSelection getWarehouseSelection() {
        return warehouseSelection;
    }

    public void setWarehouseSelection(WarehouseSelection warehouseSelection) {
        this.warehouseSelection = warehouseSelection;
    }
}
