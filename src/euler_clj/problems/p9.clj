(ns euler-clj.problems.p9
  (:require [euler-clj.math.numbers :refer [square]]))

(->> (for [a (range 1 333)
           b (range a (inc (Math/floor (/ (- 1000 a) 2))))
           c [(- 1000 a b)]
           :while (= (+ (square a) (square b))
           (square c))]
           [a b c])
     flatten
     (reduce *))

