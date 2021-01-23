package com.klasevich.lb.creator;

import com.klasevich.lb.entity.DeliveryTruck;
import com.klasevich.lb.entity.ProductType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DeliveryTruckFactory {
    private static final Logger logger = LogManager.getLogger();

    public DeliveryTruck createTruck(List<String> list) {
        DeliveryTruck truck;
        int truckId;
        boolean isLoaded;
        int capacity;
        ProductType productType;

        truckId = Integer.parseInt(list.get(0));
        String loaded = list.get(1);
        if (loaded.equals("loaded")) {
            isLoaded = true;
        } else {
            isLoaded = false;
        }
        logger.debug(loaded);
        capacity = Integer.parseInt(list.get(2));
        logger.debug(capacity);
        String type = list.get(3);
        if (type.equals("perishable")) {
            productType = ProductType.PERISHABLE;
        } else {
            productType = ProductType.NONPERISHABLE;
        }
        logger.debug(productType);
        truck = new DeliveryTruck(truckId, isLoaded, capacity, productType);
        logger.debug(truck);
        return truck;
    }
}
