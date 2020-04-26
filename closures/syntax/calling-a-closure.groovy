#!/usr/bin/env groovy

def code = { 123 }

assert code() == 123

assert code.call() == 123

def isOdd = { int i -> i % 2 != 0 }
assert isOdd(3) == true
assert isOdd.call(2) == false

def isEven = { int i -> i % 2 == 0 }
assert isOdd(3) == false
assert isOdd.call(2) == true
