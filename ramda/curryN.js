/*
  (Number, (* -> a)) => (* -> a)
*/
const curryN = (n, fn) => {
  return function() {
    if(arguments.length < fn.length) {
      return f.bind(null, ...arguments)
    }
    else {
      return f.apply(null, arguments)
    }
  }
}

module.exports = curryN
