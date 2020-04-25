#!/usr/bin/env groovy

String displayName
displayName = user.name ? user.name : 'Anonymous'
displayName = user.name ?: 'Anonymous'
