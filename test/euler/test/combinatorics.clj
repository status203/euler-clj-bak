(ns euler.test.combinatorics
  (:use clojure.test)
  (:use midje.sweet)
  (:use euler.combinatorics))

(fact (combinations# 3 1) => 3)
(fact (combinations# 3 2) => 6)
(fact (combinations# 3 3) => 6)

(fact (combinations# 10 1) => 10)
(fact (combinations# 10 2) => 90)

(fact (combination-nth [0 1 2] 1 1) => [0])
(fact (combination-nth [0 1 2] 2 1) => [1])
(fact (combination-nth [0 1 2 3 4 5 6 7 8 9] 20 2) => [2 1])