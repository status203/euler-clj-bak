(ns euler-clj.math.numbers-test
  (:require [midje.sweet :refer [tabular fact facts]]
            [euler-clj.math.numbers :refer :all]))

(tabular
 (facts "about exact integer division"
        (divides? ?m ?n) => ?result)

 ?m  ?n   ?result
 12  2    true
 12  3    true
 12  5    false
 100 20   true
 123 20   false)

(fact "first 20 Fibonacci numbers"
      (take 20 (fib 0 1)) => [0 1 1 2 3
                              5 8 13 21 34
                              55 89 144 233 377
                              610 987 1597 2584 4181])

(tabular
 (facts "about primeness"
        (prime? ?n) => ?result)

 ?n  ?result
 0   false
 1   false
 2   true
 3   true
 4   false
 97  true
 125 false)

(fact "about first twenty primes (naively)"
 (take 20 (primes-naive)) => [ 2  3  5  7 11
                               13 17 19 23 29
                               31 37 41 43 47
                               53 59 61 67 71])

(tabular
  (facts "about finding the next prime"
         (prime-next ?n ?primes) => ?result)

 ?primes ?n ?result
 []      1  2)

(fact "about first twenty primes"
 (take 20 (primes)) => [ 2  3  5  7 11
                        13 17 19 23 29
                        31 37 41 43 47
                        53 59 61 67 71])

(tabular
 (facts "about prime facts"
        (prime-factors ?n) => ?result)

 ?n    ?result
  2    [2]
  4    [2 2]
  5    [5]
  6    [2 3]
 12    [2 2 3])
