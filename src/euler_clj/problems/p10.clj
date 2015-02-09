(ns euler-clj.problems.p10
  (require [euler-clj.math.primes :refer [primes]]))

(reduce + (take-while #(< % 2000000) (primes)))

