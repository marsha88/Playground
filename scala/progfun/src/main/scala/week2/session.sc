def sum(f: Int => Int)(a: Int, b: Int): Int = {
    def sumIter(a: Int, acc: Int): Int = {
        if(a > b)
            acc
        else
            sumIter(a + 1, acc + f(a))
    }
    sumIter(a, 0)
}

def product(f: Int => Int)(a: Int, b: Int): Int = {
    def productIter(a: Int, acc: Int): Int = {
        if(a > b)
            acc
        else
            productIter(a + 1, acc * f(a))
    }
    productIter(a, 1)
}

def factorial(x: Int) = product(x => x)(1, x)


def opOf(op: (Int, Int) => Int, base: Int)(f: Int => Int)(a: Int, b: Int) = {
    def opIter(a: Int, acc: Int): Int = {
        if(a > b)
            acc
        else
            opIter(a + 1, op(acc * f(a)))
    }
    opIter(a, base)
}

def _sum = opOf(_ + _, 0)
def _product = opOf(_ * _, 1)






