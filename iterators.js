const makeRangeIterator = (start = 0, end = Infinity, step = 1) => {
    let index = start
    let iterations = 0
    return {
      [Symbol.iterator]: () => ({
       next: () => {
         if(index <= end) {
           const res = { value: index, done: false }
           index += step
           iterations++
           return res
         }
         return { value: iterations, done: true }
       }
    })
  };
}

const one_to_ten = makeRangeIterator(1, 10)

function* ranger(start = 0, end = Infinity, step = 1) {
  let i = start;
  while(i <= end) {
    yield i;
    i += step;
  }
  return i; // will be returned once all other values have been yielded.
}

let it = ranger(2, 100, 2)

for(const i of it) {
  i
}
