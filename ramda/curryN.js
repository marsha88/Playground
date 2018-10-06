/*
  (Number, (* -> a)) => (* -> a)
*/
const curryN = (n, fn) => {
  return function() {
    if(arguments.length < fn.length) {
      return fn.bind(null, ...arguments)
    }
    else {
      return fn.apply(null, arguments)
    }
  }
}


const product = (...args) => args.reduce((acc, x) => acc * x, 1)
const mul = curryN(2, product)

let a =
a

module.exports = curryN
