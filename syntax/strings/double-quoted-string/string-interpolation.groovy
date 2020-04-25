#!/usr/bin/env groovy

def name = 'Guillaume' // a plain string
def greeting = 'Hello ${name}'

// assert greeting.toString() == 'Hello Guillaume'

def sum = "The sum of 2 and 3 equals ${2 + 3}"
assert sum.toString() == 'The sum of 2 and 3 equals 5'

def person = [name: 'Guillaume', age: 36]
assert "$person.name is $person.age years old" == 'Guillaume is 36 years old'

String thing = 'treasure'
// assert 'The x-coordinate of the treasure is represented by treasure.x' ==
//     "The x-coordinate of the treasure is represented by $thing.x"    // <= Now allowed: ambiguous!!
assert 'The x-coordinate of the treasure is represented by treasure.x' ==
    "The x-coordinate of the treasure is represented by ${thing}.x"    // <= Now allowed: ambiguous!!

assert '$5' == "\$5"
assert '${name}' == "\${name}"
