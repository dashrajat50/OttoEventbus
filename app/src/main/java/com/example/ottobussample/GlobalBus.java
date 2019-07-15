package com.example.ottobussample;

import com.squareup.otto.Bus;

public class GlobalBus {
    public static Bus bus = new Bus();

    public static Bus getBus() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }
}
