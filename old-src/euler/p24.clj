(ns euler.p24
  (:use euler.combinatorics))

;; The millionth lexicographic ordering of the digits 0 - 9

(apply str (combination-nth "0123456789" 1000000 10))