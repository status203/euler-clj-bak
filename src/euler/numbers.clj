(ns euler.numbers
  (:use clojure.set))

(defn square [x] (*' x x))

(defn update-sieve-ct
  "Takes a sieve counter and a candidate and returns a counter for the same the multiplicand increased until it is not less than the candidate"
  [[multiple n] c] (vector (first (remove #(> c %)
                                               (iterate #(+' % n) multiple)))
                                n))

(defn add-to-sieve-if-prime
  "If the sieve indicates the candidate is prime, add it to the sieve"
  [s c]
  (if (< c (first (first s)))
    (conj s [(square c) c])
    s))

(defn sieve-step
  "Removes the previous first remaining candidate, updates the sieve counters until they're >= the new candidate, and adds a new counter if the candidate is prime"
  [[s [old-c c & rest-c]]]
  (let [old-cts (filter #(< (first %) c)
                               s)]
    [(add-to-sieve-if-prime (apply conj (apply disj s old-cts)
                                   (map #(update-sieve-ct % c)
                                        old-cts))
                            c)
     (cons c rest-c)]))

(defn prime-sieves
  "Returns a lazy sequence of sieve / 'remaining candidate' pairs where the sieve state is post updating all counters >= first remaining candidate"
  [] ((fn rest-ps [sc]
         (let [next-step (sieve-step sc)]
           (lazy-seq (cons next-step (rest-ps next-step)))))
      [(sorted-set [4 2] [9 3])
       (iterate (partial +' 2) 3)]))

(defn primes
  "Returns a lazy sequence of "
  [] (concat [2 3] (map #(first (second %))
          (filter #(< (first (second %)) (first (first (first %))))
                  (prime-sieves)))))

(defn prime-factors
  "Returns a vector of the prime factors of x"
  [x] (loop [ps (primes)
            acc []
            remainder x]
        (let [p (first ps)]
          (cond (= remainder 1) acc
                (> p remainder) acc
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
  (reduce #(into % (map (partial *' %2) %)) ; Take the acc so far and
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
          (reduce #(* % (apply *' (repeat (second %2) (first %2))))
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
           next-total (+' total next-x)]
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
             #(+' % (max (first %2) (second %2)))
             next-line
             (partition 2 1 largest-line))]
         (recur mapped-line rest-lines))))))

(defn amicable-numbers
  "Returns a lazy sequence of amicable number pairs"
  []
  ((fn rest-amis [x]
     (loop [candidate x]
       (let [candidate-mon-ami (apply +' (proper-divisors candidate))
             candidate-ami-ami (if (> candidate-mon-ami candidate)
                                (apply +' (proper-divisors candidate-mon-ami))
                                0)]
         (if (= candidate-ami-ami candidate)
           (lazy-seq (cons [candidate candidate-mon-ami] (rest-amis (inc candidate))))
           (recur (inc candidate))))))
   1))

(defn factorial [x]
  (loop [x x
         acc 1]
    (cond
     (< x 2) acc
     :else (recur (dec x) (*' acc x)))))

(defn factorials
  "Lazy sequence of factorials (1 based)"
  [] ( (fn rest-fcts [last-n last-fct]
         (let [next-fct (*' last-fct (inc last-n))]         
           (lazy-seq (cons next-fct
                           (rest-fcts (inc last-n) next-fct)))))
       0 1N))

(defn perfect-classification
  "Returns :perfect, :deficient, or :abundant"
  [x] (let [pd-sum (apply +' (proper-divisors x))]
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
                    (+' sum r))))))