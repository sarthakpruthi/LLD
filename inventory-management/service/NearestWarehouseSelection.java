package service;

import entity.User;
import entity.Warehouse;

import java.util.List;

public class NearestWarehouseSelection implements WarehouseSelection {

    @Override
    public Warehouse getWarehouse(List<Warehouse> warehouses){
        return warehouses.get(0);
    }
}
