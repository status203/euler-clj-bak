(ns euler.test.numbers
  (:use clojure.test)
  (:use midje.sweet)
  (:use euler.numbers))

;; Primes

;; update-sieve-ct
(fact (update-sieve-ct [4 2] 3) => [4 2])
(fact (update-sieve-ct [4 2] 5) => [6 2])
(fact (update-sieve-ct [8 2] 3) => [8 2])
(fact (update-sieve-ct [15 3] 16) => [18 3])

;; add-to-sieve-if-prime
(fact (add-to-sieve-if-prime [[6 2] [9 3]] 5) => [[6 2] [9 3] [25 5]])
(fact (add-to-sieve-if-prime [[4 2] [9 3]] 4) => [[4 2] [9 3]])

;; sieve-step
(fact (sieve-step [(sorted-set [4 2] [9 3]) [3 5 7 9]]) => [(sorted-set [6 2] [9 3] [25 5]) [5 7 9]])
(fact (sieve-step [(sorted-set [4 2] [9 3] [25 5]) [5 7 9 11]]) => [(sorted-set [8 2] [9 3] [25 5] [49 7]) [7 9 11]])
(fact (sieve-step [(sorted-set [4 2] [9 3] [25 5] [49 7]) [7 9 11 13]]) => [(sorted-set [9 3] [10 2] [25 5] [49 7]) [9 11 13]])


;; primes

(fact (take 20 (primes)) => [ 2  3  5  7 11
                             13 17 19 23 29
                             31 37 41 43 47
                             53 59 61 67 71])

;; prime-factors
(fact (prime-factors 0) => [])
(fact (prime-factors 1) => [])
(fact (prime-factors 2) => [2])
(fact (prime-factors 10) => [2 5])
(fact (prime-factors 600) => [2 2 2 3 5 5])

;; number-of-factors
(fact (number-of-factors 1) => 1)
(fact (number-of-factors 2) => 2)
(fact (number-of-factors 10) => 4)
(fact (number-of-factors 600) => 24)

;; factors
(fact (factors 1) => #{1})
(fact (factors 2) => #{1 2})
(fact (factors 3) => #{1 3})
(fact (factors 4) => #{1 2 4})
(fact (factors 12) => #{1 2 3 4 6 12})
(fact (factors 60) => #{1 2 3 4 5 6 10 12 15 20 30 60})

;; proper-divisors
(fact (proper-divisors 1) => #{})
(fact (proper-divisors 2) => #{1})
(fact (proper-divisors 3) => #{1})
(fact (proper-divisors 4) => #{1 2})
(fact (proper-divisors 12) => #{1 2 3 4 6})
(fact (proper-divisors 60) => #{1 2 3 4 5 6 10 12 15 20 30})

;; proper-factors
(fact (proper-factors 1) => #{})
(fact (proper-factors 2) => #{})
(fact (proper-factors 3) => #{})
(fact (proper-factors 4) => #{2})
(fact (proper-factors 12) => #{2 3 4 6})
(fact (proper-factors 60) => #{2 3 4 5 6 10 12 15 20 30})

;; prime
(fact (prime? 1) => false)
(fact (prime? 2) => true)
(fact (prime? 3) => true)
(fact (prime? 4) => false)
(fact (prime? 5) => true)
(fact (prime? 9) => false)
(fact (prime? 53) => true)

;; smallest-common-multiple
(fact (lowest-common-multiple 1 2) => 2)
(fact (lowest-common-multiple 6 10) => 30)
(fact (lowest-common-multiple 3 9) => 9)
(fact (lowest-common-multiple 7 13) => 91)

;; Fibonacci
(fact (take 10 (fib)) => [1 1 2 3 5 8 13 21 34 55])

;; Triangular numbers
(fact (take 10 (triangular-numbers)) => [1 3 6 10 15 21 28 36 45 55])
