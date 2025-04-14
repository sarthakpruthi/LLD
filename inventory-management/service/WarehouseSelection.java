package service;

import entity.Warehouse;

import java.util.List;

public interface WarehouseSelection {
    Warehouse getWarehouse(List<Warehouse> warehouses);
}
