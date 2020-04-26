def fib
fib = { long n -> n < 2 ? n : fib(n - 1) + fib(n - 2) }
assert fib(15) == 610 // slow!


fib = fib.memoize()
assert fib(25) == 75025 // fast!
