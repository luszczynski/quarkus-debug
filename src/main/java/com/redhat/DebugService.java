package com.redhat;

import javax.enterprise.context.ApplicationScoped;

/**
 * DebugService
 */
@ApplicationScoped
public class DebugService {

    private int counter = 0;

    public int increment() {
      return this.counter++;
    }
}