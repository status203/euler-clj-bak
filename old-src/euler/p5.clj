;; 5. What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

(ns euler.p5
  (:use euler.numbers))

(apply lowest-common-multiple (range 1 21))
