#!/usr/bin/env groovy

def list = [0, 1, 2, 3, 4]
assert list[2] == 2
list[2] = 4
assert list[0..2] == [0, 1, 4]
list[0..2] = [6, 6, 6]
assert list == [6, 6, 6, 3, 4]
