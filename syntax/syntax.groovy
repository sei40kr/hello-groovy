#!/usr/bin/env groovy

// 1. Comment

// 1.1 Single-line comment

// a standalone single line comment
println "hello" // a comment till the end of the line


// 1.2 Multiline comment

/* a standalone multiline comment
   spanning two lines */
println "hello" /* a multiline comment starting
                   a the end of the statement */
println 1 /* one */ + 2 /* two */


// 1.3 Groovydoc comment
/**
 * A Class description
 */
class Person {
    /** the name of the person */
    String name

    /**
     * Creates a greeting method for a certain person.
     *
     * @param otherPerson the person to greet
     * @return a greeting message
     */
    String greet(String otherPerson) {
        "Hello ${otherPerson}"
    }
}


// 3. Identifiers

// 3.2 Quoted identifiers

def map = [:]

map."an identifier with a space and double quotes" = "ALLOWED"
map.'with-dash-signs-and-single-quotes' = "ALLOWED"

map.'single quote'
map."double quote"
map.'''triple single quote'''
map."""triple double quote"""
map./slashy string/
map.$/dollar slashy string/$

def firstname = "Homer"
map."Simpson-${firstname}" = "Homer Simpson"

assert map.'Simpson-Homer' == "Homer Simpson"


// 4. Strings

// 4.1 Single-quoted string

'a single-quoted string'


// 4.2 String concatenation

assert 'ab' == 'a' + 'b'


// 4.3 Triple-single-quoted string

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


// 4.3.2 Unicode escape sequence

'The Euro currency symbol: \u20AC'


// 4.4 Double-quoted string

"a double-quoted string"


// 4.4.1 String interpolation

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


// 4.4.2 Special case of interpolating closure expressions

def sParameterLessClosure = "1 + 2 == ${-> 3}"
assert sParameterLessClosure == '1 + 2 == 3'

def sOneParamClosure = "1 + 2 == ${ w -> w << 3}"
assert sOneParamClosure == '1 + 2 == 3'

def number = 1
def eagerGString = "value == ${number}"
def lazyGString = "value == ${ -> number }"

assert eagerGString == "value == 1"
assert lazyGString  == "value == 1"

number = 2
assert eagerGString == "value == 1"
assert lazyGString  == "value == 2"


// 4.5 Triple-double-quoted string

name = 'Groovy'
def template = """
    Dear Mr ${name},

    You're the winner of the lottery!

    Yours sincerly,

    Dave
"""

assert template.toString().contains("Groovy")


// 4.6 Slashy string

def fooPattern = /.*foo.*/
assert fooPattern == '.*foo.*'

def escapeSlash = /The character \/ is a forward slash/
assert escapeSlash == 'The character / is a forward slash'

def multilineSlashy = /one
    two
    three/

assert multilineSlashy.contains('\n')

def color = 'blue'
def interpolatedSlashy = /a ${color} car/

assert interpolatedSlashy == 'a blue car'


// 4.7 Dollar slashy string

name = "Guillaume"
def date = "April, 1st"
def dollarSlashy = $/
    Hello $name,
    today we're ${date}.

    $ dollar sign
    $$ escaped dollar sign
    \ backslash
    / forward slash
    $/ escaped forward slash
    $$$/ escaped opening dollar slashy
    $/$$ escaped closing dollar slashy
/$

assert [
    'Guillaume',
    'April, 1st',
    '$ dollar sign',
    '$ escaped dollar sign',
    '\\ backslash',
    '/ forward slash',
    '/ escaped forward slash',
    '$/ escaped opening dollar slashy',
    '/$ escaped closing dollar slashy'
].every { dollarSlashy.contains(it) }
