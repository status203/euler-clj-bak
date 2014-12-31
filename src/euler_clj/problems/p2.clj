(ns euler-clj.problems.p2
  (:require [euler-clj.math.numbers :refer [fib]]))

(->> (take-while #(<= % 4000000) (fib 1 2))
     (filter even?)
     (reduce +))
