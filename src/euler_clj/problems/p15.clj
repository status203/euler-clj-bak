(ns euler-clj.problems.p15
  (:require [euler-clj.math.numbers :refer [factorial]]))

(defn lattice-path-count
  [m n]
  (/ (factorial (+ m n)) (*' (factorial m) (factorial n))))

(lattice-path-count 20 20)
