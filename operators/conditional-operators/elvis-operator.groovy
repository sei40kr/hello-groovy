#!/usr/bin/env groovy

displayName = user.name ? user.name : 'Anonymous'
displayName = user.name ?: 'Anonymous'
