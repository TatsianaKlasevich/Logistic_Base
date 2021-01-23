package com.klasevich.lb.entity;

import com.klasevich.lb.exception.TerminalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DeliveryTruck extends Thread {
    private static final Logger logger = LogManager.getLogger();
    private static final int TIME_MINUTES_FOR_ONE_CAPACITY = 1;
    private int truckId;
    private boolean isLoaded;
    private int capacity;
    private ProductType productType;

    public DeliveryTruck() {
    }

    public DeliveryTruck(int truckId, boolean isLoaded, int capacity, ProductType productType) {
        this.truckId = truckId;
        this.isLoaded = isLoaded;
        this.capacity = capacity;
        this.productType = productType;
        if (productType.equals(ProductType.PERISHABLE)) {
            this.setPriority(Thread.MAX_PRIORITY);
        } else {
            this.setPriority(Thread.MIN_PRIORITY);
        }
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
        if (productType.equals(ProductType.PERISHABLE)) {
            this.setPriority(Thread.MAX_PRIORITY);
        } else {
            this.setPriority(Thread.MIN_PRIORITY);
        }
    }

    @Override
    public void run() {
        Optional<Terminal> terminal = Optional.empty();
        int time = getTimeforService(this.getCapacity());
        try {
            terminal = LogisticBase.getInstance().getTerminal(this);
            if (this.isLoaded) {
                unload(this, time);
            } else {
                load(this, time);
            }
        } catch (TerminalException e) {
            logger.error("There are problems with terminal");
        } finally {
            terminal.ifPresent(currentTerminal -> LogisticBase.getInstance().terminalRelease(this, currentTerminal));
        }
    }

    public void unload(DeliveryTruck truck, long time) {
        try {
            TimeUnit.MINUTES.sleep(time);
            logger.info("Delivery truck {} unloading {} milliseconds",
                    truck.getTruckId(), time);
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted {}", Thread.currentThread().getName() + e);
        }
    }

    public void load(DeliveryTruck truck, long time) {
        try {
            TimeUnit.MINUTES.sleep(time);
            logger.info("Delivery truck {} loading {} milliseconds",
                    truck.getTruckId(), time);
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted {}", Thread.currentThread().getName() + e);
        }
    }

    private int getTimeforService(int capacityOfTruck) {
        return capacityOfTruck * TIME_MINUTES_FOR_ONE_CAPACITY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryTruck that = (DeliveryTruck) o;

        if (truckId != that.truckId) return false;
        if (isLoaded != that.isLoaded) return false;
        if (capacity != that.capacity) return false;
        return productType == that.productType;
    }

    @Override
    public int hashCode() {
        int result = truckId;
        result = 31 * result + (isLoaded ? 1 : 0);
        result = 31 * result + capacity;
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeliveryTruck{");
        sb.append("truckId=").append(truckId);
        sb.append(", isLoaded=").append(isLoaded);
        sb.append(", capacity=").append(capacity);
        sb.append(", productType=").append(productType);
        sb.append('}');
        return sb.toString();
    }
}
