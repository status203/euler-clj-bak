(ns euler-clj.math.numbers)

(def collatz-length
  (memoize (fn [n]
             (cond
              (= n 1) 1
              (even? n) (inc (collatz-length (bit-shift-right n 1)))
              :else (inc (collatz-length (inc (* 3 n))))))))

(reduce #(if (> (first %2) (first %1)) %2 %1)
        (map (fn [index] (vector (collatz-length index) index))
             (range 1 1000000)))