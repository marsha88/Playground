/*
  (* -> a) => (* -> a)
*/
const curry = (fn) => (...args) => fn.bind(null, ...args)

module.exports = curry