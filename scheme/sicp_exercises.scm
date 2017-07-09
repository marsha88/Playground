#| Exercise 1.3 |#
(define (square x)
  (* x x)
)

(define (sumOfSquares x y)
  (+ (square x) (square y))
)

(define (sumOfLargestSquares x y z)
  (if (or (> x y) (> x z))
    (if (> y z) (sumOfSquares x y) (sumOfSquares x z))
    (sumOfSquares y z)
  )
)

(sumOfLargestSquares 2 3 1)


#| Exercise 1.4 |#
; 1:+ 2:- 3:* 4:/

(define (operation x)
  (cond
    ((= x 1) +)
    ((= x 2) -)
    ((= x 3) *)
    ((= x 4) /)
  )
)

(define (apply a b op)
  ((operation op) a b)
)

(apply 2 3 4)

#| Newton's Square Root method |#

(define (avg x y)
  (/ (+ x y) 2)
)

(define (sqrt x)
  (define (improve guess)
    (avg guess (/ x guess))
  )
  (define (good-enough guess)
    (< (abs (- (square guess) x)) 0.001)
  )
  (define (sqrt-iter guess)
    (if (good-enough guess)
        guess
        (sqrt-iter (improve guess))
    )
  )
  (if (> x 0)
      (sqrt-iter 1.0)
      -1
  )
)
(sqrt 2)


#| The new-if hypothesis |#

#|
   This iff cannot be used for the sqrt function because this iff statement uses applicative order
   evaluation instead of normal order evaluation. Basically, the built-in if statement lazily evaluates
   its arguments while the one implemented here tries to unwrap the arguments which means it will continue calling
   sqrt-iter over and over until you've reached the maximum recursion depth.
|#

(define (iff predicate then-clause else-clause)
  (cond
    (predicate then-clause)
    (else else-clause)
  )
)

(iff (= 0 0) 1 2)
