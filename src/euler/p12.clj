;; 12. What is the value of the first triangle number to have over five hundred divisors?


(defn first-tri-over-x-factors [x]
  (first (filter #(> (number-of-factors %) x) (triangular-numbers))))

(first-tri-over-x-factors 500)