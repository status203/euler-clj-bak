(ns euler.numbers
  (:use clojure.set))

(defn square [x] (* x x))

(defn sieve-compare [x y]
  (cond
   (< (first x) (first y)) -1
   (> (first x) (first y)) 1
   (< (second x) (second y)) -1
   (> (second x) (second y)) 1
   :else 0))

(defn sieve-candidate
  "Takes the sieve and list of candidates from the previous iteration and returns the sieve and list of candidates after sifting the next candidate"
  [sieve+candidates]
  (let [
        [sieve candidates] sieve+candidates
        next-candidates (rest candidates)
        x (first next-candidates)
        to-be-advanced (vec (take-while #(< (first %) x) sieve))
        interim-sieve (apply (partial disj sieve) to-be-advanced)
        advanced (map
                  (fn [tba] (first (filter #(>= (first %) x) (iterate #(vector (+ (first %) (second %)) (second %)) tba))))
                  to-be-advanced)
        new-sieve (reduce #(conj % %2) interim-sieve advanced)]
    (vector new-sieve next-candidates)))

(defn primes
  "Lazy sequence of primes" ; using an iterative sieve.
  []
  ((fn rest-primes [sieve+candidates]
     (cond
      (empty? sieve+candidates) (lazy-seq (concat
                                           [2 3]
                                           (rest-primes (vector
                                                         (sorted-set-by sieve-compare [4 2] [9 3])
                                                         (iterate (partial + 2) 3)))))
      :else (let [next-prime-s+c (first
                                  (filter
                                   #(let [
                                          candidate (first (second %))
                                          lowest-in-sieve (first (first (first %)))]
                                      (< candidate lowest-in-sieve))
                                   (iterate sieve-candidate (sieve-candidate sieve+candidates))))
                  [sieve candidates] next-prime-s+c
                  next-prime (first (second next-prime-s+c))
                  next-sieve (conj sieve [(square next-prime) next-prime])]
              (lazy-seq (cons
                         next-prime
                         (rest-primes [next-sieve candidates]))))))
   []))

(defn prime-factors
  "Returns a vector of the prime factors of x"
  [x] (loop [ps (primes)
            acc []
            remainder x]
       (let [p (first ps)]
         (cond (= remainder 1) acc
               (> (square p) x) acc
               (zero? (mod remainder p)) (recur ps (conj acc p) (quot remainder p))
               :else (recur (rest ps) acc remainder)))))

(defn number-of-factors [x]
  "Calculate the number of factors, via prime factors, without reconstructing every factor"
  (cond
   (= x 1) 1
   :else (apply * (map
                   #(inc (second %))
                   (frequencies (prime-factors x))))))

(defn factors
  "Return a set of divisors of x." ; ... calculated by combining the prime factors
  [x]
  (reduce #(into % (map (partial * %2) %)) ; Take the acc so far and
                                        ; concat with the acc times by the
                                        ; next prime factor
          #{1}
          (prime-factors x)))

(defn prime?
  [x]
  (= (count (factors x)) 2))

(defn lowest-common-multiple
  ([] 1) ; Analagous to *?
  ([x] x)
  ([x & rest]
     (->> (cons x rest)
          (map (comp frequencies prime-factors))
          (apply concat)
          (reduce #(assoc % (first %2) (max (second %2) (get % (first %2) 0)))
                  {}) ; Get only the highest freq for each prime factor.
          (reduce #(* % (apply * (repeat (second %2) (first %2))))
                  1)))) ; Raise each prime factor to the appropriate
                        ; power and mulitply together.

(defn proper-divisors [x] (disj (factors x) x))
(defn proper-factors [x] (disj (proper-divisors x) 1))

(defn fib
  "Lazy Fibonacci sequence"
  []
  (concat [1 1]
          ((fn rest-terms [last-one last-but-one]
             (let [next-term (+' last-one last-but-one)]
               (lazy-seq (cons next-term (rest-terms next-term last-one)))))
           1 1)))

(defn triangular-numbers []
  ((fn rest-tris [total last-x]
     (let [next-x (inc last-x)
           next-total (+ total next-x)]
       (lazy-seq (cons next-total (rest-tris next-total next-x)))))
   0 0))

(defn largest-sum-through-triangle [lines]
  (let
      [reversed-lines (reverse lines)]
    (loop [largest-line (first reversed-lines), remaining-lines (rest reversed-lines)]
      (cond
       (empty? remaining-lines) (max largest-line)
       :else
       (let
           [next-line (first remaining-lines)
            rest-lines (rest remaining-lines)
            mapped-line 
            (map
             #(+ % (max (first %2) (second %2)))
             next-line
             (partition 2 1 largest-line))]
         (recur mapped-line rest-lines))))))

(defn amicable-numbers
  "Returns a lazy sequence of amicable number pairs"
  []
  ((fn rest-amis [x]
     (loop [candidate x]
       (let [candidate-mon-ami (apply + (proper-divisors candidate))
             candidate-ami-ami (if (> candidate-mon-ami candidate)
                                (apply + (proper-divisors candidate-mon-ami))
                                0)]
         (if (= candidate-ami-ami candidate)
           (lazy-seq (cons [candidate candidate-mon-ami] (rest-amis (inc candidate))))
           (recur (inc candidate))))))
   1))

(defn factorial [x]
  (loop [x x
         acc (/ x x)]
    (cond
     (< x 2) acc
     :else (recur (dec x) (* acc x)))))

(defn factorials
  "Lazy sequence of factorials (1 based)"
  [] ( (fn rest-fcts [last-n last-fct]
         (let [next-fct (* last-fct (inc last-n))]         
           (lazy-seq (cons next-fct
                           (rest-fcts (inc last-n) next-fct)))))
       0 1N))

(defn perfect-classification
  "Returns :perfect, :deficient, or :abundant"
  [x] (let [pd-sum (apply + (proper-divisors x))]
        (cond (< pd-sum x) :deficient
              (> pd-sum x) :abundant
              :else :perfect)))

(defn perfect-classifications
  "Returns a lazy sequence that classifies each index (starting at 1)"
  [] ((fn rest-pc [x]
        (lazy-seq (cons (perfect-classification x) (rest-pc (inc x))))) 1))

(defn sum-digits [x]
  (loop [x x, sum 0]
    (cond
     (zero? x) sum
     :else (let [r (rem x 10), q (quot x 10)]
             (recur q
                    (+ sum r))))))