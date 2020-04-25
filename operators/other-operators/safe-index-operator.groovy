#!/usr/bin/env groovy

String[] array = ['a', 'b']
assert 'b' == array?[1]
array?[1] = 'c'
assert 'c' == array?[1]

array = null
assert null == array?[1]
array?[1] = 'c'
assert null == array?[1]

def personInfo = [name: 'Daniel.Sun', location: 'Shanghai']
assert 'Daniel.Sun' == personInfo?['name']
personInfo?['name'] = 'sunlan'
assert 'sunlan' == personInfo?['name']

personInfo = null
assert null == personInfo?['name']
personInfo?['name'] = 'sunlan'
assert null == personInfo?['name']
