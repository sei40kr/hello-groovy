#!/usr/bin/env groovy

class MyCallable {
    int call(int x) {
        2 * x
    }
}

def mc = new MyCallable()
assert mc.call(2) == 4
assert mc(2) == 4
