#!/usr/bin/env groovy

'''a triple-single-quoted string'''

def aMultilineString = '''line one
line two
line three'''

def startingAndEndingWithANewline = '''
line one
line two
line three
'''

def strippedFirstNewline = '''\
line one
line two
line three
'''

assert !strippedFirstNewline.startsWith('\n')
