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

(sqrt 4)

#| Cubed Root |#
(define (cube x)
  (* x x x)
)

(define (cubert x)
  (define (improve guess)
    (/ (+ (/ x (square guess)) (* 2 guess)) 3)
  )
  (define (good-enough guess)
    (< (abs (- (cube guess) x)) 0.001)
  )
  (define (cubert-iter guess)
    (if (good-enough guess)
        guess
        (cubert-iter (improve guess))
    )
  )
  (cubert-iter 1.0)
)

(cubert 27)


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


#| Number of ways you can be given change |#

(define (changeOptions amount)
  (cc amount 5))

(define (cc amount coin)
    (cond
      ((or (< amount 0) (= coin 0)) 0)
      ((or(= amount 0) (= amount 1)) 1)
      (else
        (+ (cc (- amount (denomination coin)) coin) (cc amount (- coin 1))))
      )
    )
)
(define (denomination amount)
  (cond ((= amount 1) 1)
    ((= amount 2) 5)
    ((= amount 3) 10)
    ((= amount 4) 25)
    ((= amount 5) 50))
)
(changeOptions 100)
