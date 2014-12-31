(ns euler-clj.problems.p1
  (:require [euler-clj.math.numbers :refer [divides?]]))

(->> (range 1 1000)
     (filter #(or (divides? % 3)
                  (divides? % 5)))
     (reduce +))
