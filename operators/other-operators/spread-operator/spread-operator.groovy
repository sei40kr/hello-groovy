#!/usr/bin/env groovy

import groovy.transform.Canonical

class Car {
    String make
    String model
}
def cars = [
    new Car(make: 'Peugeot', model: '508'),
    new Car(make: 'Renault', model: 'Clio')
]
def makes = cars*.make
assert makes == ['Peugeot', 'Renault']


// The spread operator is null-safe, meaning that if an element of the
// collection is null, it will return null instead of throwing a
// NullPointerException:

cars = [
    new Car(make: 'Peugeot', model: '508'),
    null,
    new Car(make: 'Renault', model: 'Clio')
]
makes = cars*.make
assert makes == ['Peugeot', null, 'Renault']


class Make {
    String name
    List<Model> models
}

@Canonical
class Model {
    String name
}

cars = [
    new Make(name: 'Peugeot',
             models: [new Model('408'), new Model('508')]),
    new Make(name: 'Renault',
             models: [new Model('Clio'), new Model('Captur')])
]

makes = cars*.name
assert makes == ['Peugeot', 'Renault']

def models = cars*.models*.name
assert models == [['408', '508'], ['Clio', 'Captur']]
assert models.sum() == ['408', '508', 'Clio', 'Captur'] // flatten one level
assert models.flatten() == ['408', '508', 'Clio', 'Captur'] // flatten all levels


cars = [
    [
        new Car(make: 'Peugeot', model: '408'),
        new Car(make: 'Peugeot', model: '508')
    ], [
        new Car(make: 'Renault', model: 'Clio'),
        new Car(make: 'Renault', model: 'Captur')
    ]
]
models = cars.collectNested { it.model }
assert models == [['408', '508'], ['Clio', 'Captur']]
