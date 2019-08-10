import R from "ramda";

/* transducing for map */
const array = [1, 2, 3, 4, 5];

const double = x => x * 2;
const triple = x => x * 3;
const isEven = x => x % 2 === 0;

const output1 = array.map(double).map(triple);
output1;

const output2 = array.map(
  R.compose(
    double,
    triple
  )
);
output2;

/* transducers are compose for reducers */

const arrayReducer = (acc, processedItem) => [...acc, processedItem];

const transducer = transform => conj => {
  return (acc, current) => {
    const mapped = double(current);
    return conj(acc, mapped);
  };
};

function map(transform) {
  return function transduce(conj) {
    return (acc, current) => {
      const transformed = transform(current);
      return conj(acc, transformed);
    };
  };
}

function filter(prop) {
  return function transduce(conj) {
    return (acc, current) => {
      if (prop(current)) {
        return conj(acc, current);
      }
      return acc;
    };
  };
}

const pipeline = R.compose(
  map(double),
  filter(isEven)
);

let a = array.reduce(pipeline(arrayReducer), []);
a;
