(ns euler-clj.problems.p5
  (:require [euler-clj.math.numbers :refer [prime-factors freq-merge freq-expand]]))

(->> (range 2 21)
     (map prime-factors)
     (map frequencies)
     (reduce freq-merge)
     freq-expand
     (reduce *))