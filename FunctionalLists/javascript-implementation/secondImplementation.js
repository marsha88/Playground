/*
This second implementation creates the same list as before, but only uses functions and booleans.
*/

function emptyList(selector){
  return selector(null, null, true);
}

function isEmpty(list){
  return list(function(h, t, e){
    return e;
  });
}

function prepend(el, list) {
  return function(selector) {
    return selector(el, list, false);
  };
};

function head(list) {
  return list(function(h, t, e) {
    return h;
  });
};

function tail(list) {
  return list(function(h, t, e) {
    return t;
  });
};

const printList = function(list){
  if( !isEmpty(list) ){
    console.log(head(list));
    printList(tail(list));
  }
};

/*
let myList = prepend(3, prepend(4, prepend(5, emptyList)));
printList(myList);
*/

/* logical operators needed for number functions */
const and = (a, b) => {
  if(a){
    if(b){
      return true;
    }
  }
  return false;
};

const or = (a, b) => {
  if(a){
    return true;
  }
  if(b){
    return true;
  }
  return false;
};

const not = (a) => {
  if(a){
    return false;
  }
  return true;
};


/* Building numbers out of lists */

const zero = emptyList;

const inc = (number) => {
  return prepend(emptyList, number);
};

const dec = (number) => {
  return tail(number);
};

const isZero = isEmpty;

const add = (a, b) => {
  if(isZero(a)){
    return b;
  }
  else{
    return add(dec(a), inc(b));
  }
};

const sub = (a, b) => {
  if(isZero(b)){
    return a;
  }
  return sub(dec(a), dec(b));
};

const mul = (a, b) => {
  if(isZero(b)){
    return zero;
  }
  return add(a, mul(a, dec(b)));
};

const pow = (a, b) => {
  if(isZero(b)){
    return prepend(emptyList, emptyList);
  }
  return mul(a, pow(a, dec(b)));
};

const equal = (a, b) => {
  if(and(isZero(a), isZero(b))){
    return true;
  }
  else if(or(isZero(a), isZero(b))){
    return false;
  }
  return equal(dec(a), dec(b));
};

const lessThan = (a, b) => {
  if(or(isZero(a), isZero(b))){
    if(and(isZero(a), not(isZero(b)))){
      return true;
    }
    return false;
  }
  return lessThan(dec(a), dec(b));
}

const greaterThan = (a, b) => {
  return lessThan(b, a);
};

const div = (a, b) => {
  if(lessThan(a, b)){
    return zero;
  }
  return inc(div(sub(a, b), b));
};

const rem = (a, b) => {
  if(lessThan(a, b)){
    return a;
  }
  return rem(sub(a,b), b);
};

/* grab numeric value from list. Basically a leng function */
const parseNum = (num) => {
  if(isZero(num)){
    return 0;
  }
  return 1 + parseNum(dec(num));
};

console.log(parseNum(div(inc(inc(zero)), inc(zero))));
