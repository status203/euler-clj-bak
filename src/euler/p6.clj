;; 6. Find the difference between the sum of the squares of the fist one hundred natural numbers and the square of the sum.

(ns euler.p6
  (:use euler.numbers))

(defn sum-square-diff [x]
  (-
   (square (apply + (range 1 (inc x))))
   (apply + (map square (range 1 (inc x))))))

(sum-square-diff 100) 

