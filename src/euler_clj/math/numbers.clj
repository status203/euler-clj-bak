(ns euler-clj.math.numbers)

(defn divides?
  "Whether integer n divides integer m without remainder"
  [m n] (zero? (mod m n)))

(defn fib
  "Creates an infinite sequence of Fibonacci numbers starting with m and n"
  [m n] (cons m
              (lazy-seq (fib n (+' m n)))))

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

(defn prime-next
  "Produce the next prime given a sequence of the lower primes
  at least up to the square root of n."
  [n primes]
  (case primes
    [] 2
    [2] 3
    (loop [candidates (iterate (partial + 2) (+ n 2))]
      (let [upper-bound (Math/floor (Math/sqrt n))
            relevant-primes (take-while (partial >= upper-bound) primes)]
        (let [candidate (first candidates)]
          (if (not-any? (partial divides? candidate) primes)
            candidate
            (recur (rest candidates))))))))

(defn primes
  "Returns a lazy infinite sequence of primes"
  [] (concat [2 3 5] ((fn r-primes [n prior-primes]
   (let [next-prime (prime-next n prior-primes)
         next-primes (concat prior-primes [next-prime])]
     (lazy-seq (cons next-prime (r-primes next-prime next-primes))))) 5 [2 3 5])))


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
