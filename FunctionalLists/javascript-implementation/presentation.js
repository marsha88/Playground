
function truthy(t, f) {
  return t();
}

function falsey(t, f) {
  return f();
}
function ifelse(conditional, then, el) {
  return conditional(then, el)
}

const nil = function() {}

const empty = function(op) {
  op(nil, nil, truthy)
}

function isEmpty(list) {
  return list(function(h, t, e) {
    return e;
  })
}

function prepend(head, tail) {
  return function(op) {
    return op(head, tail, falsey)
  }
}

function head(list) {
  return list(function(head, tail, e) {
    return head;
  })
}

function tail(list) {
  return list(function(head, tail, e) {
    return tail;
  })
}

function length(list) {
  const zero = function() {
    return 0;
  };

  const tailLen = function() {
    return 1 + length(tail(list));
  };

  return ifelse(isEmpty(list), zero, tailLen)
}

const myList = prepend(1, prepend(2, prepend(3, empty)))
//console.log(head(myList))
console.log(length(empty))
//console.log(length(tail(myList)))
