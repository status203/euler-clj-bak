;; 9. Find the only Pythagorean triplet, {a, b, c}, for which a + b + c = 100.

(ns euler.p9
  (use euler.numbers))

(defn pythag-triplets [total]
  (for [x (range 1 (/ total 3)), y (range x (/ total 2))
        :let [z (- total x y)]
        :while (and
                (< y z) 
                (<= (+ (square x) (square y)) (square z))) ; loop once the sum of the squares becomes higher the the square of the hypoteneuse
        :when (= (+ (square x) (square y)) (square z))]
    [x y z]))

(apply * (first (pythag-triplets 1000)))