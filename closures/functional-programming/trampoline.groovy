def factorial
// Closures are wrapped in a TrampolineClosure. Upon calling, a trampolined
// Closure will call the original Closure waiting for its result. If the outcome
// of the call is another instance of a TrampolineClosure, created perhaps as a
// result to a call to the trampoline() method, the Closure will again be
// invoked. This repetitive invocation of returned trampolined Closures instances
// will continue until a value other than a trampolined Closure is returned.
// That value will become the final result of the trampoline. That way, calls are
// made serially, rather than filling the stack.
factorial = { int n, def accu = 1G ->
    if (n < 2) return accu
    factorial.trampoline(n - 1, n * accu)
}
factorial = factorial.trampoline()

assert factorial(1) == 1
assert factorial(3) == 1 * 2 * 3
assert factorial(1000) // == 402387260.. plus another 2560 digits
