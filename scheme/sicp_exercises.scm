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

;; Alyssa P. Hacker Solutions
(define make-interval cons)
(define lower-bound car)
(define upper-bound cdr)

(define (add-interval x y)
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))
(define (sub-interval x y)
 (make-interval (- (lower-bound x) (lower-bound y))
                (- (upper-bound x) (upper-bound y)))
)

(define (mul-interval x y)
  (let ((p1 (* (lower-bound x) (lower-bound y)))
        (p2 (* (lower-bound x) (upper-bound y)))
        (p3 (* (upper-bound x) (lower-bound y)))
        (p4 (* (upper-bound x) (upper-bound y))))
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))


(define (div-interval x y)
  (if (or (= 0 (upper-bound y)) (= 0 (lower-bound y)))
    (cons 0 0)
    (mul-interval x
                  (make-interval (/ 1.0 (upper-bound y))
                                 (/ 1.0 (lower-bound y))))))

;; Exercise 2.12
;; Build a procedure make-center-percentage such that given a center and percentage returns a correct interval
;; Also build a procedure percentage such that given an interval returns the correct interval percentage

(define (center i)
  (/ (+ (lower-bound i) (upper-bound i)) 2))

(define (make-center-percentage c perc)
  (let ((percOfC (* c (/ perc 100))))
    (make-interval (- c percOfC) (+ c percOfC))))

(define (percentage interval)
  (let ((c (center interval)))
    ( * 100 (/ (- (upper-bound interval) c) c))))

(lower-bound (make-center-percentage 15 100))
(upper-bound (make-center-percentage 15 100))

(percentage (make-center-percentage 15 100))

(define nil 0)

(define my-list (cons 1 (cons 2 (cons 3 (cons 4 nil)))))

(define (list-ref items n)
  (if (= n 0)
(car items)
(list-ref (cdr items) (- n 1))))

;; Append
(define (append l1 l2)
(if (null? l1) l2
(cons (car l1) (append (cdr l1) l2))))

(define list1 (list 1 2 3 4))
(define list2 (list 5 6 7 8))

(append list1 list2)

;;Last Pair Exercise 2.17
(define (last-pair x)
(if (null? (cdr x)) x
  (last-pair (cdr x))))

;;Reverse (won't compile usig mit/scheme)
(define nil '())
(define (reverse y)
  (define (reverse-iter x res)
  (if (null? x) res
    (reverse-iter (cdr x) (cons (car x) res))))
  (reverse-iter y nil)
)

;;Exercise 2.20
(define (same-parity x . y)
  (let ((parity (mod x 2)))
      (define (same-parity-iter remains result)
        (if (null? remains) (reverse result)
        (if (= (mod (car remains) 2) parity)
           (same-parity-iter (cdr remains) (cons (car remains) result))
           (same-parity-iter (cdr remains) result))
        ))
      (same-parity-iter y (list x))
  )
)

(same-parity 1 2 3 4 5 6 7 9 11)

;;Mappppps
(define (map list proc)
 (if (null? list) nil
     (cons (proc (car list)) (map (cdr list) proc))
     ))


(define test-list (list 1 2 3 4 5))
(map test-list (lambda (x) (* 2 x)))

(define (square-list items)
  (if (null? items)
nil
(cons (* (car items) (car items)) (square-list (cdr items)))))


(define (square-list-map items)
(map items (lambda (x) (* x x))))


;; Filter
(define (filter list proc)
  (if (null? list) nil
      (if (proc (car list))
          (cons (car list) (filter (cdr list) proc))
          (filter (cdr list) proc))))

;; for-each Exercies 2.23
(define (for-each list proc)
  (cond ((null? list) nil)
      (else (proc (car list))
            (for-each (cdr list) proc))))


(define (count-leaves x)
  (if (null? x) 0
    (if (pair? x)
      (+ (count-leaves (car x)) (count-leaves (cdr x)))
      1))
)

;; Exercise 2.25
(define first (cons 1 (cons 3 (list (list 5 7) 9))))
(car (cdr (car (cdr (cdr first)))))

(define second (list (list 7)))
(car (car second))

(define third (list 1 (list 2 (list 3 (list 4 (list 5 (list 6 7)))))))
(car (cdr (car (cdr (car (cdr (car (cdr (car (cdr (car (cdr third))))))))))))

;;Exercise 2.26
(define x (list 1 2 3))
(define y (list 4 5 6))
(append x y) ;; (1 2 3 4 5 6)
(cons x y) ;; ((1 2 3) 4 5 6)
(list x y) ;;((1 2 3) (4 5 6))

;;Exercise 2.27
(define (deep-reverse list)
  (define (deep-reverse-iter x res)
    (if (null? x) res
      (if (not (pair? x))
        x ;;return single value
        (deep-reverse-iter (cdr x) (cons (deep-reverse-iter (car x) nil) res)) ;;return a deep-reverse of the innner list
      )
    ))
    (deep-reverse-iter list nil)
)
