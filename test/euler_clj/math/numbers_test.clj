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
