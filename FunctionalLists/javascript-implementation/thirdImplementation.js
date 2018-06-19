/* Because the first two were too verbose */

const truthy = (t, f) => t()
const falsey = (t, f) => f()
const ify = (b, t, e) => b(t, e)

const prepend = (head, tail) => (selector) => selector(head, tail, falsey)

const empty = (selector) => selector(null, null, truthy)

const isEmpty = (list) => list((h, t, e) => e)

const tail = list => list((head, tail, e) => tail)

const head = list => list((head, tail, e) => head)

const create = (...elems) => elems.reduce((acc, elem) => prepend(elem, acc), empty) // this reverses things.

const my_list = create(5, "hello", { name: "Astro" })

const length = (list) => ify(isEmpty(list), () => 0, () => (1 + length(tail(list))))

const map = (f, list) => ify(isEmpty(list), () => list, () => prepend(f(head(list)), map(f, tail(list))))

const print = (list) => ify(isEmpty(list), () => {}, () => { console.log(head(list)); print(tail(list));  })

print(map(n => 2 * n, prepend(1, prepend(2, prepend(3, empty)))))

const each = (f, list) => {
  if(list === empty) {
    return;
  }
  else {
    f(head(list))
    each(f, tail(list))
  }
}

// TODO: map, fold, reverse
