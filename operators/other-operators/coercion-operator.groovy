#!/usr/bin/env groovy

Integer x = 123
// Integer is not assignable to a String, so it will produce a
// ClassCastException at runtime
// String s = (String) x
String s = x as String


class Identifiable {
    String name
}
class User {
    Long id
    String name
    def asType(Class target) {
        if (target == Identifiable) {
            return new Identifiable(name: name)
        }
        throw new ClassCastException("User cannot be coerced into $target")
    }
}
def u = new User(name: 'Xavier')
def p = u as Identifiable
assert p instanceof Identifiable
assert !(p instanceof User)
