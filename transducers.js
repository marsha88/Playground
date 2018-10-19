
/* transducer version of map */
const mapper = (...funcs) => (list) =>
  list.reduce((acc, x) => [...acc, funcs.reduce((val, f) => f(val), x)], [])


/* transducer version of filter */
const filterer = (...funcs) => (list) =>
  list.reduce((acc, x) => {
    const propositions = funcs.reduce((val, f) => val && f(x), true)
    if(propositions) {
      return [...acc, x]
    }
    return acc
  }, [])
