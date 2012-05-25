;;16. What is the sum of the digits of the number 2^1000?
 
(defn clj-pow [base power]
  (loop [mult base, iters power, total 1]
    (cond
     (zero? iters) total
     :else (recur mult (dec iters) (* total base)))))

(defn sum-digits [x]
  (loop [x x, sum 0]
    (cond
     (zero? x) sum
     :else (let [r (rem x 10), q (quot x 10)]
             (recur q (+ sum r))))))

(sum-digits (clj-pow 2N 1000))
