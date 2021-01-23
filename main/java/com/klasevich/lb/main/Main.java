package com.klasevich.lb.main;

import com.klasevich.lb.creator.QueueOfTruckCreator;
import com.klasevich.lb.entity.DeliveryTruck;

import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        QueueOfTruckCreator creator = new QueueOfTruckCreator();
        Queue<DeliveryTruck> truckQueue = creator.createQueue("data/truck.txt");
        System.out.println(truckQueue);

        for (DeliveryTruck truck : truckQueue) {
            truck.start();
        }
    }
}
