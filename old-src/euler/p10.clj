;; 10. Calculate the sum of all the primes below two million.

(ns euler.p10
  (:use euler.numbers))

(apply + (take-while #(< % 2000000) (primes)))
