#!/usr/bin/env groovy

def str = 'example of method reference'
def fun = str.&toUpperCase
def upper = fun()
assert upper == str.toUpperCase()


def transform(List elements, Closure action) {
    def result = []
    elements.each {
        result << action(it)
    }
    result
}
String describe(Person p) {
    "$p.name is $p.age"
}
def action = this.&describe
def list = [
    new Person(name: 'Bob', age: 42),
    new Person(name: 'Julia', age: 35)
]
assert transform(list, action) == ['Bob is 42', 'Julia is 35']


def foo = BigInteger.&new
def fortyTwo = foo('42')
assert fortyTwo == 42G


def instanceMethod = String.&toUpperCase
assert instanceMethod('foo') == 'FOO'
