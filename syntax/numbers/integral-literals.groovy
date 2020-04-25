#!/usr/bin/env groovy

// Binary literal
int xInt = 0b10101111
assert xInt == 175

// Octal literal
xInt = 077
assert xInt == 63

xInt = 0x77
assert xInt == 119
