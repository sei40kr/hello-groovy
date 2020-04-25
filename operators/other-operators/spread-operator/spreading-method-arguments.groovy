#!/usr/bin/env groovy

int function(int x, int y, int z) {
    x * y + z
}

def args = [4, 5, 6]

assert function(*args) == 26

args = [4]
assert function(*args, 5, 6) == 26
