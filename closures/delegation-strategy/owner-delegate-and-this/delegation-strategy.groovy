#!/usr/bin/env groovy

class Person {
    String name
    def pretty = { "My name is $name" }
    String toString() {
        pretty()
    }
}
class Thing {
    String name
}
def p = new Person(name: 'Igor')
def cl = { name.toUpperCase() }
cl.delegate = p
assert cl() == 'IGOR'

// Let's illustrate the default "owner first" strategy with this code:
p = new Person(name: 'Sarah')
def t = new Thing(name: 'Teapot')

assert p.toString() == 'My name is Sarah'
p.pretty.delegate = t
assert p.toString() == 'My name is Sarah'

// It is possible to change the resolution strategy of the closure
p.pretty.resolveStrategy = Closure.DELEGATE_FIRST
assert p.toString() == 'My name is Teapot'
