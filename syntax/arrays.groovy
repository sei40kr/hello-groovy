#!/usr/bin/env groovy

String[] arrStr = ['Ananas', 'Banana', 'Kiwiwi']

assert arrStr instanceof String[]
assert !(arrStr instanceof List)

def numArr = [1, 2, 3] as int[]

assert numArr instanceof int[]
assert numArr.size() == 3

def matrix3 = new Integer[3][3]
assert matrix3.size() == 3

Integer[][] matrix2
matrix2 = [[1, 2], [3, 4]]
assert matrix2 instanceof Integer[][]

String[] names = ['Cedric', 'Guillaume', 'Jochen', 'Paul']
assert names[0] == 'Cedric'

names[2] = 'Blackdrag'
assert names[2] == 'Blackdrag'
