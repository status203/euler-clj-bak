(ns euler.p23
  (use [euler.numbers :only [perfect-classifications]]))

;; Find the sum of all the positive integers which cannot be written
;; as the sum of two abundant numbers.

(let [abundants (map first (filter #(= :abundant (second %))
                                   (take 28123 (map-indexed
                                                #(vector (inc %) %2)
                                                (perfect-classifications)))))]
  (apply + (clojure.set/difference (set (range 1 28123))
                                   (set (filter #(> 28124 %) (for [x abundants
                                                                   y abundants]
                                                               (+ x y)))))))