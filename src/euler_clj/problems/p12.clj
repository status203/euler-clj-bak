(ns euler-clj.problems.p12
  (:require [euler-clj.math.numbers :refer [triangular-numbers number-of-factors]]
            [euler-clj.math.primes :refer [primes]]))

(let [p (primes) ]
  (->> (triangular-numbers)
       (map #(vector % (number-of-factors p %)))
       (remove #(> 500 (second %)))
       (first)))
