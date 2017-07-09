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
(define (sqrt x)
  (if (> x 0)
      (sqrt-iter 1.0 x)
      -1
  )
)

(define (sqrt-iter guess x)
  (if (good-enough guess x)
      guess
      (sqrt-iter (improve guess x) x)
  )
)

(define (avg x y)
  (/ (+ x y) 2)
)

(define (improve guess x)
  (avg guess (/ x guess))
)

(define (good-enough guess x)
  (< (abs (- (square guess) x)) 0.001)
)

(sqrt 2)
