(ns euler-clj.problems.p6
  (:require [euler-clj.math.numbers :refer [square]]))

(-
 (->> (range 1 101)
      (reduce +')
      square)

 (->> (range 1 101)
      (map square)
      (reduce +')))
