#!/usr/bin/env groovy

def text = "some text to match"
def m = text ==~ /match/
assert m instanceof Boolean
if (m) {
    throw new RuntimeException("Should not reach that point!")
}
