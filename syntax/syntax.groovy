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


// 5. Numbers

// 5.1 Integral literals

// Binary literal
int xInt = 0b10101111
assert xInt == 175

// Octal literal
xInt = 077
assert xInt == 63

xInt = 0x77
assert xInt == 119


// 5.2 Decimal literals

assert 1e3 == 1_000.0
assert 2E4 == 20_000.0
assert 3e+1 == 30.0
assert 4E-1 == 0.04
assert 5e-1 == 0.5


// 5.4 Number type suffixes

assert 42I == new Integer('42')
assert 42i == new Integer('42') // lowercase i more readable
assert 123L == new Long("123") // uppercase L more readable
assert 2147483648 == new Long('2147483648') // Long type used, value too large for an Integer
assert 456G == new BigInteger('456')
assert 456g == new BigInteger('456')
assert 123.45 == new BigInteger('123.45')
assert 1.200065D == new Double('1.200065')
assert 1.234F == new Float('1.234')
assert 1.23E23D == new Double('1.23E23')
assert 0b1111L.class == Long // binary
assert 0xFFi.class == Integer // hexadecimal
assert 034G.class == BigInteger // octal

// base and exponent are ints and the result can be represented by an Integer
assert 2 ** 3 instanceof Integer // 8
assert 10 ** 9 instanceof Integer // 1_000_000_000

// the bese is a long, so fit the result in a Long
// (although it could have fir in an Integer)
assert 5L ** 2 instanceof Long // 25

// the result can't be represented as an Integer or Long, so return a BigInteger
assert 100 ** 10 instanceof BigInteger // 10e20

// the base is a BigDecimal and the exponent a negative int
// but the result can be represented as an Integer
assert 0.5 ** -2 instanceof Integer // 4

// the base is an int, and the exponent a negative float
// but again, the result can be represented as an Integer
assert 1 ** -0.3f instanceof Integer // 1

// the base is an int, and the exponent a negative int
// but the result will be calculated as a Double
// (both base and exponent are actually converted to doubles)
assert 10 ** -1 instanceof Double // 0.1

// the base is a BigDecimal, and the exponent is an int, so return a BigDecimal
assert 1.2 ** 10 instanceof BigDecimal // 6.1917364224

// the base is a float or double, and the exponent is an int
// but the result can only be represented as a Double value
assert 3.4f ** 5 instanceof Double //  454.35430372146965
assert 5.6d ** 2 instanceof Double //  31.359999999999996

// the exponent is a decimal value
// and the result can only be represented as a Double value
assert 7.8 ** 1.9 instanceof Double //  49.542708423868476
assert 2 ** 0.1f instanceof Double //  1.0717734636432956


// 6. Booleans

def myBooleanVariable = true
boolean untypedBooleanVar = false
booleanField = true


// 7. Lists

def numbers = [1, 2, 3]

assert numbers instanceof List
assert numbers.size() == 3

def heterogeneous = [1, "a", true]

def arrayList = [1, 2, 3]
assert arrayList instanceof java.util.ArrayList

def linkedList = [2, 3, 4] as LinkedList
assert linkedList instanceof java.util.LinkedList

LinkedList otherLinked = [3, 4, 5]
assert otherLinked instanceof java.util.LinkedList

def multi = [[0, 1], [2, 3]]
assert multi[1][0] == 2


// 8. Arrays

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


// 9. Maps

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
