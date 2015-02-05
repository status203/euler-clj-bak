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

(defn sieve-update-lowest-multiple
  "Adds the relevant amount to the first counter"
  [[_ _  multiples :as sieve]]
  (let [[prime multiple] (peek multiples)]
    (assoc-in sieve [2 prime] (+ multiple prime))))

(defn sieve-counters-for-next-candidate
  "Repeatedly update lowest multiple until lowest multiple is >= candidate
  (does not update primes or candidates)"
  [[_ [candidate :as candidates]  _ :as sieve]]
  (let [[_ _ new-multiples :as new-sieve] (sieve-update-lowest-multiple sieve)]
    (if (< (second (peek new-multiples)) candidate)
      (recur new-sieve)
      new-sieve)))

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

(defn prime-sieves
  "Returns a lazy sequence of sieves, each of which 'finds' a new prime"
  ([] (let [initial-sieve [[2] (iterate #(+ 2 %) 3) (priority-map 2 2)]]
        (prime-sieves 2 initial-sieve)))
  ([prev-prime prev-sieve]
   (let [next-sieve (sieve-next-candidate-update prev-sieve)
         next-sieve-last-prime (peek (first next-sieve))]
     (if (= next-sieve-last-prime prev-prime)
       (prime-sieves prev-prime next-sieve)
       (lazy-seq (cons next-sieve (prime-sieves next-sieve-last-prime next-sieve)))))))

(defn primes-by-iterative-sieve
  "Lazy sequence of primes using an iterative sieve"
  [] (cons 2 (map #(peek (first %)) (prime-sieves))))

(def primes primes-by-prior-primes)

(defn prime-factors
  "Generates a sequence of prime factors of n"
  [n] (loop [m n
             factors []
             possible-factors (primes)]
        (if (<= m 1)
          factors
          (let [possible-factor (first possible-factors)]
           (if (divides? m possible-factor)
             (recur (quot m possible-factor)
                    (conj factors possible-factor)
                    possible-factors)
             (recur m factors (rest possible-factors)))))))

