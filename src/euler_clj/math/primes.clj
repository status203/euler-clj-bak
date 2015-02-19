(ns euler-clj.math.primes
  (:require [clojure.data.priority-map :refer [priority-map]]))

(defn- divides? ; requiring math.numbers creates a circular dependency
  "Whether integer n divides integer m without remainder"
  [m n] (zero? (mod m n)))

(defn prime?
  "Checks whether n is prime"
  [n] (if (< n 2)
        false
        (->> (range 2 (inc (Math/floor(Math/sqrt n))))
             (filter #(divides? n %))
             empty?)))

(defn primes-naive
 "Generates an infinite sequence of prime numbers by checking each number
  for primeness"
 [] (filter prime? (range)))

(defn prime-next [n primes]
             (case primes
               (loop [candidates (iterate (partial + 2) (+ n 2))]
                 (let [upper-bound (Math/floor (Math/sqrt n))
                       relevant-primes (take-while (partial >= upper-bound) primes)]
                   (let [candidate (first candidates)]
                     (if (not-any? (partial divides? candidate) primes)
                       candidate
                       (recur (rest candidates))))))))

(defn primes-by-prior-primes
  "Returns a lazy infinite sequence of primes"
  [] (concat [2 3 5] ((fn r-primes [n prior-primes]
                          (let [next-prime (prime-next n prior-primes)
                                next-primes (concat prior-primes [next-prime])]
                            (lazy-seq (cons next-prime (r-primes next-prime next-primes)))))
                        5 [2 3 5])))

;; sieve passed around as
;; [vec-of-primes-so-far lazy-seq-of-candidates priority-map-of-multiples]

(defn sieve-counters-for-next-candidate
  "Repeatedly update lowest multiple until lowest multiple is >= candidate
  (does not update primes or candidates)"
  [[_ [candidate :as candidates] multiples :as sieve]]
  (loop [[_ _ multiples :as sieve] sieve]
    (let [[prime multiple] (peek multiples)]
          (if (>= multiple candidate)
            sieve
            (recur (assoc-in sieve [2 prime] (+ multiple prime)))))))

(defn sieve-next-candidate-update
  "Updates all components of the sieve for the next candidate"
  [[_ [candidate & rest-candidates]  multiples :as sieve]]
    (-> sieve
        sieve-counters-for-next-candidate
        (assoc-in [1] rest-candidates)
        ((fn [[primes _ multiples :as sieve]]
           (if (< candidate (second (peek multiples)))
             (-> sieve
                 (update-in [0] #(conj % candidate))
                 (update-in [2] #(assoc % candidate candidate)))
             sieve)))))

(defn primes-by-iterative-sieve
  "Lazy sequence of primes using an iterative sieve"
  []
  (let [initial-sieve [[2] (iterate #(+ 2 %) 3) (priority-map 2 2)]]
    (->> (iterate sieve-next-candidate-update initial-sieve)
         (map #(peek (first %)))
         (partition 2 1)
         (remove #(= (first %) (first (rest %))))
         (map first))))

(def primes primes-by-iterative-sieve)

(defn prime-factors
  "Generates a sequence of prime factors of n, can optionally provide a list of primes."
  ([n] (prime-factors (primes) n))
  ([possible-factors n]
    (loop [m n
           factors []
           possible-factors possible-factors]
        (if (<= m 1)
          factors
          (let [possible-factor (first possible-factors)]
           (if (divides? m possible-factor)
             (recur (quot m possible-factor)
                    (conj factors possible-factor)
                    possible-factors)
             (recur m factors (rest possible-factors))))))))
