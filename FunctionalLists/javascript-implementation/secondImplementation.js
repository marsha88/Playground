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

let myList = prepend(3, prepend(4, prepend(5, emptyList)));
printList(myList);


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
