;; 12. What is the value of the first triangle number to have over five hundred divisors?

(ns euler.p12
  (:use euler.nmbers))

(defn first-tri-over-x-factors [x]
  (first (filter #(> (number-of-factors %) x) (triangular-numbers))))

(first-tri-over-x-factors 500)