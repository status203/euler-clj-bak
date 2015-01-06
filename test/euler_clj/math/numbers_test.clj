(ns euler-clj.math.numbers-test
  (:require [midje.sweet :refer [tabular fact facts just]]
            [euler-clj.math.numbers :refer :all]))

(tabular
 (facts "about squares"
        (square ?n) => ?result)

 ?n  ?result
 0   0
 1   1
 2   4
 10  100
 5/2 25/4)

(tabular
 (facts "about exact integer division"
        (divides? ?m ?n) => ?result)

 ?m  ?n   ?result
 12  2    true
 12  3    true
 12  5    false
 100 20   true
 123 20   false)

(tabular
 (facts "first 20 Fibonacci numbers"
      (take 8 (fib ?m ?n)) => ?result)

 ?m ?n ?result
 0  1  [0 1 1 2 3 5 8 13]
 1  1  [1 1 2 3 5 8 13 21])

(+  3 5)

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

(tabular
 (facts "about frequency upserts"
        (freq-upsert ?m ?k ?v) => ?result)

 ?m         ?k   ?v  ?result
 {1 2 3 4}  3    1   {1 2 3 4}
 {1 2 3 4}  3    5   {1 2 3 5}
 {1 2 3 4}  5    6   {1 2 3 4 5 6})

(fact "about frequency merges"
      (freq-merge {1 1 2 2 3 3 4 4} {1 1 2 1 3 4 5 5}) => {1 1 2 2 3 4 4 4 5 5})

(fact "about frequency expansion"
      (freq-expand {:a 3 :b 2 :c 1}) => (just [:a :a :a :b :b :c] :in-any-order))
