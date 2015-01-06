;;16. What is the sum of the digits of the number 2^1000?

(ns euler.p16
  (:use euler.numbers))

(defn clj-pow [base power]
  (loop [mult base, iters power, total 1]
    (cond
     (zero? iters) total
     :else (recur mult
                  (dec iters)
                  (* total base)))))

(sum-digits (clj-pow 2N 1000))
