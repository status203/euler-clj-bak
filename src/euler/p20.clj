(ns euler.p20
  (:use euler.numbers))

;; 20. Find the sum of the digits in the number 100!
   
(defn fct [x]
  (loop [y x, acc 1]
    (cond
     (= y 1) acc
     :else (recur (dec y) (* acc y)))))

(sum-digits (fct 100M))

