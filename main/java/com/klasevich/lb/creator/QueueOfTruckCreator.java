package com.klasevich.lb.creator;

import com.klasevich.lb.entity.DeliveryTruck;
import com.klasevich.lb.parser.FileParser;
import com.klasevich.lb.reader.FileReader;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueOfTruckCreator {
    FileReader reader = new FileReader();
    FileParser parser = new FileParser();
    DeliveryTruckFactory factory = new DeliveryTruckFactory();
    TruckComparator comparator = new TruckComparator();

    public Queue<DeliveryTruck> createQueue(String fileName) {
        List<String> linesOfTruckData = reader.readingFromFile(fileName);
        Queue<DeliveryTruck> trucks = new PriorityQueue<>(comparator.reversed());
        for (String line : linesOfTruckData) {
            List<String> truckData = parser.parseLines(line);
            DeliveryTruck truck = factory.createTruck(truckData);
            trucks.add(truck);
        }
        return trucks;
    }
}
