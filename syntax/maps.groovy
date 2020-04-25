#!/usr/bin/env groovy

def colors = [red: '#ff0000', green: '#00ff00', blue: '#0000ff']

assert colors['red'] == '#ff0000'
assert colors.green == '#00ff00'

colors['pink'] = '#ff00ff'
colors.yellow = '#ffff00'

assert colors.pink == '#ff00ff'
assert colors['yellow'] == '#ffff00'

assert colors instanceof java.util.LinkedHashMap

assert colors.unknown == null

numbers = [1: 'one', 2: 'two']

assert numbers[1] == 'one'

def key = 'name'
person = [key: 'Guillaume']

assert !person.containsKey('name')
assert person.containsKey('key')

person = [(key): 'Guillaume']

assert person.containsKey('name')
assert !person.containsKey('key')
