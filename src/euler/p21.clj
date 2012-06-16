
(ns euler.p21
  (:use euler.numbers))

;;21. Evaluate the sum of all the amicable numbers under 10000

(defn <10k? [x] (< x 10000))
(apply + (flatten (take-while #(and (<10k? (first %)) (<10k? (second %))) (amicable-numbers))))
