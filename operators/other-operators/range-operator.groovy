#!/usr/bin/env groovy

def range = 0..5
assert (0..5).collect() == [0, 1, 2, 3, 4, 5]
assert (0..<5).collect() == [0, 1, 2, 3, 4]
assert (0..5) instanceof List
assert (0..5).size() == 6

assert ('a'..'d').collect() == ['a', 'b', 'c', 'd']
