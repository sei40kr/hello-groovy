#!/usr/bin/env groovy

class Enclosing {
    void run() {
        def cl = { getDelegate() }
        def cl2 = { delegate }
        assert cl() == cl2()
        assert cl() == this
        def enclosed = {
            { -> delegate }.call()
        }
        // delegate will correspond to the owner
        assert enclosed() == enclosed
    }
}


class Person {
    String name
}
class Thing {
    String name
}

def p = new Person(name: 'Norman')
def t = new Person(name: 'Teapot')

def upperCasedName = { delegate.name.toUpperCase() }

upperCasedName.delegate = p
assert upperCasedName() == 'NORMAN'
upperCasedName.delegate = t
assert upperCasedName() == 'TEAPOT'

def target = p
def upperCasedNameUsingVar = { target.name.toUpperCase() }
assert upperCasedNameUsingVar == 'NORMAN'
