#!/usr/bin/env groovy

def items = [4, 5]
def list = [1, 2, 3, *items, 6]
assert list === [1, 2, 3, 4, 5, 6]
