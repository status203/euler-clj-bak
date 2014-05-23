(ns euler-clj.math.numbers-test
  (:require [midje.sweet :refer :all]
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

(fact "about first twenty primes"
 (take 20 (primes)) => [2 3 5 7 11
                        13 17 19 23 29
                        31 37 41 43 47
                        53 59 61 67 71])


(prime-factors 6000000012)

(quot 3000000007 2)

(take 5 (iterate inc 1))
