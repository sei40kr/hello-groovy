#!/usr/bin/env groovy

def greeting = { 'Hello, $it!' }
// is equivalent to this one:
// def greeting = { it -> 'Hello, $it!' }
assert greeting('Patrick') == 'Hello, Patrick!'

def magicNumber = { -> 42 }

// this call will fail because the closure doesn't accept any argument
// magicNumber(11)
