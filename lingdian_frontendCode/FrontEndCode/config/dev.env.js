'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')


BASE_API: module.exports = merge(prodEnv, {
  NODE_ENV: '"development"'
}),

'"http://192.168.xx.xx"'
