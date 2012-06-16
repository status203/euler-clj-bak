(ns euler.combinatorics
  (:use [euler.numbers :only [factorial]]))

(defn combinations#
  "Returns the number of combinations possible of k length from n items"
  [n k] (apply * (range (inc (- n k)) (inc n))))

(defn combination-nth
  "Returns the nth (ordered by original position in collection) combination of k items (or all items by default)"
  ;;[coll n] (combination-nth coll n (count coll))
  [coll n k] (loop [coll coll
                    n n
                    k k
                    acc []]
               (cond
                (empty? coll) acc
                (zero? k) acc
                :else (let [sub-combinations (combinations# (dec (count coll)) (dec k))
                            first-item-index (quot (dec n) sub-combinations)
                            first-item (nth coll first-item-index)
                            sub-n (inc (mod (dec n) sub-combinations))]
                        (recur (filter #(not= first-item %) coll)
                               sub-n
                               (dec k)
                               (conj acc first-item))))))
