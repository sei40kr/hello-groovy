#!/usr/bin/env groovy

class User {
    public final String name;
    User(String name) { this.name = name }
    String getName() { "Name: $name"  }
}

def user = new User('Bob')
assert user.name == 'Name: Bob'
assert user.@name == 'Bob'
