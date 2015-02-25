(ns euler-clj.problems.p16
  (:require [euler-clj.math.numbers-as-text :refer [digits]]))

(->> (reduce *' (repeat 1000 2))
     digits
     (reduce +))