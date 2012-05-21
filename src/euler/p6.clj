;; 6. Find the difference between the sum of the squares of the fist one hundred natural numbers and the square of the sum.

(defn sum-square-diff [x]
  (-
   (#(* % %) (apply + (range 1 (inc x))))
   (apply + (map #(* % %) (range 1 (inc x))))))

(sum-square-diff 100) 

