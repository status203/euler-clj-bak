(ns euler-clj.problems.p7
  (:require [euler-clj.math.numbers :refer [primes]]))

(last (take 10001 (primes)))