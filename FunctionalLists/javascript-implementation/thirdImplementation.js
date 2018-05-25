/* Because the first two were too verbose */

const prepend = (head, tail) => (selector) => selector(head, tail)

const empty = null

const tail = list => list((head, tail) => tail)

const head = list => list((head, tail) => head)

const create = (...elems) => elems.reduce((acc, elem) => prepend(elem, acc), null)

const my_list = create(5, "hello", { name: "Astro" })

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
