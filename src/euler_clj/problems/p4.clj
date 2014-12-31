(ns euler-clj.problems.p4
  (:require [euler-clj.math.numbers-as-text :refer [palindromic?]]))

(->> (for [m (range 100 1000)
           n (range 100 (inc m))] (* m n))
     (filter palindromic?)
     (sort-by identity >)
     first)

