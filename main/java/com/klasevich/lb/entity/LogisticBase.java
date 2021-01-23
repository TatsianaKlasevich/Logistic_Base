package com.klasevich.lb.entity;

import com.klasevich.lb.exception.TerminalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private static final Logger logger = LogManager.getLogger();
    private static LogisticBase instance;
    private static final int BASE_SIZE = 4;
    private Semaphore semaphore = new Semaphore(BASE_SIZE, true);
    private List<Terminal> terminals = new ArrayList<>();
    private static final ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean baseCreated = new AtomicBoolean();

    private LogisticBase() {
        for (int i = 0; i < BASE_SIZE; i++) {
            terminals.add(new Terminal(i));
        }
    }

    public static LogisticBase getInstance() {
        if (!baseCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new LogisticBase();
                }
                baseCreated.set(true);

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Optional<Terminal> getTerminal(DeliveryTruck truck) throws TerminalException {
        Optional<Terminal> usingTerminal = Optional.empty();
        try {
            semaphore.acquire();
            lock.lock();
            for (Terminal terminal : terminals) {
                if (!terminal.isBusy()) {
                    terminal.setBusy(true);
                }
                logger.info("Delivery truck {} took terminal",
                        truck.getTruckId());
                usingTerminal = Optional.of(terminal);
                return usingTerminal;
            }
        } catch (InterruptedException e) {
            throw new TerminalException(e);
        } finally {
            lock.unlock();
        }
        return usingTerminal;
    }

    public void terminalRelease(DeliveryTruck truck, Terminal terminal) {
        terminal.setBusy(false);
        logger.info("Delivery truck {} released",
                truck.getTruckId());
        semaphore.release();
    }
}
