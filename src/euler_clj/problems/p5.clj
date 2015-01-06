(ns euler-clj.problems.p5
  (:require [euler-clj.math.numbers :refer [least-common-multiple]]))

(apply least-common-multiple (range 2 21))
