(ns euler-clj.problems.p7
  (:require [euler-clj.math.primes :refer [primes]]))

(last (take 10001 (primes)))
