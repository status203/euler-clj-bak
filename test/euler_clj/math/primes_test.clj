(ns euler-clj.math.primes-test
  (:require [euler-clj.math.primes :refer :all]
            [midje.sweet :refer [tabular fact facts just]]))

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

#_(tabular
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
 (facts "about prime factors"
        (prime-factors ?n) => ?result)

 ?n    ?result
  2    [2]
  4    [2 2]
  5    [5]
  6    [2 3]
 12    [2 2 3])

