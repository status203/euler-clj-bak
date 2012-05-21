;; What is the 10,001st prime number

(ns euler.p7
  (:use euler.numbers))

(nth (primes) 10000)
