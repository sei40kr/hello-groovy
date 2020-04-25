#!/usr/bin/env groovy

def person = Person.find { it.id == 123 }
def name = person?.name
assert name == null
