#!/usr/bin/env groovy

import java.util.regex.Pattern

def p = ~/foo/
assert p instanceof Pattern
