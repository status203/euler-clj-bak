(ns euler-clj.math.numbers
  (:require [euler-clj.math.primes :refer [prime-factors]]))

(defn square
  "Returns n multiplied by itself"
  [n] (* n n))

(defn divides?
  "Whether integer n divides integer m without remainder"
  [m n] (zero? (mod m n)))

(defn fib
  "Creates an infinite sequence of Fibonacci numbers starting with m and n"
  ([] (fib 0 1))
  ([m n] (lazy-seq (cons m
                        (fib n (+' m n))))))

(defn freq-upsert
  "If new value does not exist in freq map (as key) then insert with given
  frequency, otherwise set frequency to largest of existing or given"
  [m value freq] (update-in m
                            [value]
                            (fn [old new] ((fnil max 0) old new))
                            freq))

(defn freq-merge
  "Merge two frequency maps. If there are duplicates in either sequence the result
  should contain the larger frequency of the duplicate item"
  [a b] (if-let [[k v] (first a)]
          (freq-merge (dissoc a k) (freq-upsert b k v))
          b))

(defn freq-expand
  "Expand map of values (map key) and frequencies (map value) into sequence
  of values"
  [m] (reduce (fn [acc [k v]] (concat (repeat v k) acc))
              []
              m))

(defn least-common-multiple
  "Calculates the least common multiple of the supplied numbers. In analogy to *
  the lcm of no numbers returns 1"
  ([] 1 )
  ([n] n)
  ([n & rest] (->> (cons n rest)
                   (map prime-factors)
                   (map frequencies)
                   (reduce freq-merge)
                   (freq-expand)
                   (reduce *))))

