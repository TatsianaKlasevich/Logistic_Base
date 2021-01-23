package com.klasevich.lb.creator;

import com.klasevich.lb.entity.DeliveryTruck;

import java.util.Comparator;

public class TruckComparator implements Comparator<DeliveryTruck> {
    @Override
    public int compare(DeliveryTruck o1, DeliveryTruck o2) {
        return o1.getPriority() - o2.getPriority();
    }
}
