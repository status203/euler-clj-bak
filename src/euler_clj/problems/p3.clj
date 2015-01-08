(ns euler-clj.problems.p3
  (:require [euler-clj.math.primes :refer [prime-factors]]))

(last (prime-factors 600851475143))
