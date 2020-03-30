package com.redhat;

import javax.enterprise.context.ApplicationScoped;

/**
 * DebugService
 */
@ApplicationScoped
public class DebugService {

    private int counter = 0;

    public void increment() {
        this.counter++;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }
}