#!/usr/bin/env groovy

import java.util.regex.Matcher

def text = "some text to match"
def m = text =~ /match/
assert m instanceof Matcher
if (!m) {   // equivalent to calling if (!m.find(0))
    throw new RuntimeException("Oops, text not found!")
}
