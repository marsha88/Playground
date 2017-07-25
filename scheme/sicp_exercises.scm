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


; Number of ways you can be given change

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

(define (denomination amount)
  (cond ((= amount 1) 1)
    ((= amount 2) 5)
    ((= amount 3) 10)
    ((= amount 4) 25)
    ((= amount 5) 50))
)
(changeOptions 100)

; Exponentiation
(define (even x)
	(= (remainder x 2) 0)
)

(define (alt-expo b n)
	(if (= n 0) 1
	(if (even n) (square (alt-expo b (/ n 2)))
		(* b (alt-expo b (- n 1))))
	)
)

(alt-expo 5 3)

; Euclid's Algorithm
(define (gcd a b)
	(if (= b 0) a
	(gcd b (remainder a b))
	)
)

(gcd 16 28)

; Higher Order Procedures and lambda functions
(define (funcToReturnFunc x)
  (lambda ()
    (* x 2)
  )
)

((funcToReturnFunc 3))

;lambda functions stored in variable
(define plus5 (lambda (x) (+ x 5)))

(define (callLambda func x)
  (func x)
)

(callLambda plus5 10)

; Using let expressions

(define (magnitude x y)
(let ((a (square x))
        (b (square y)))
  (sqrt (+ a b))
))

; let expressions are built on lambdas. Here is the same magnitude function, but using the lambda implementation.
(define (magnitudeUsingLambda x y)
  ((lambda (a b)
    (sqrt (+ a b))
  )
  (square x)
  (square y)
  )
)

; These give the same output
(magnitude 2 2)
(magnitudeUsingLambda 2 2)

;let values are as local as possible since the code using the newly defined variables is literally in the body of the let statement
(define x 5)
(+ (let ((x 3))
        (+ x (* x 10)))
x)
(define (inc x)
  (+ x 1)
)
;Exercise 1.41
(define (applyTwice proc)
  (lambda (x)
    (proc (proc x))
  )
)

((applyTwice inc) 5)

; Pairs

(define doubleTuple (cons 1 2))

(+ (car doubleTuple) (cdr doubleTuple))

(define list (cons 1 (cons 2 (cons 3 (cons 4 5)))))
(define (head list)
  (car list)
)
(define (tail list)
  (cdr list)
)
(head (tail (tail (tail list))))

; It's been long enough. Here is how you print to stdout
(display "Hello, world.")

; Exercise 2.1
(define (negative x)
  (if (< x 0) x (* -1 x))
)

(define (print-rational x)
  (newline)
  (display (car x))
  (display "/")
  (display (cdr x)))

(define (make-rational x y)
  (if (> (/ x y) 0) (cons (abs x) (abs y))
    (cons (negative x) (abs y))
  )
)

(print-rational (make-rational 3 -2))

(define (my-cons x y)
  (lambda (m)
    (cond ((= m 0) x)
          ((= m 1) y)
          (else (error "Argument not 0 or 1 -- CONS" m))))
)
(define (grabHead z) (z 0))
(define (grabTail z) (z 1))

(define nums (my-cons 1 2))

(grabHead nums)

(define (alt-cons x y)
  (lambda (m)
    (m x y)
  )
)

(define (alt-car z)
  (z (lambda (p q) p))
)

(define (alt-cdr z)
  (z (lambda (p q) q))
)

(define testCons (alt-cons 2 5))

(alt-car testCons)
(alt-cdr testCons)

;;Modeling numbers as procedures
(define (add-1 n)
  (lambda (f)
    (lambda (x)
      (f ((n f) x))
    )
  )
)

(define zero (lambda (f) (lambda (x) x)))
(define one (lambda (f) (lambda (x) (f x))))
(define two (lambda (f) (lambda (x) (f (f x)))))


;; Implement add procedure
(define (add a b)
 (lambda (f)
  (lambda (x)
    ((a f) ((b f) x))
  )
 )
)
