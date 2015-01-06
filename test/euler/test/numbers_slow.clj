;; A home for tests that cause the tests to run too slowly on my netbook.

(ns euler.test.numbers-slow
  (:use clojure.test)
  (:use midje.sweet)
  (:use euler.numbers))

;; Amicable numbers
(fact (take 2 (amicable-numbers)) => [[220 284] [1184 1210]])

