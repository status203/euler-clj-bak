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

(defn primes-niaive
 "Generates an infinite sequence of prime numbers by checking each number for primeness"
 [] (filter prime? (range)))

(defn prime-next
  "Produce the next prime greater than n, given a sequence of the lower primes
  at least up to the square root of n. Assumes that "
  [n primes]
    (loop [candidates (iterate inc (inc n))]
      (let [upper-bound (Math/floor (Math/sqrt n))
            relevant-primes (take-while (partial >= upper-bound) primes)]
        (let [candidate (first candidates)]
          (if (not-any? (partial divides? candidate) primes)
            candidate
            (recur (rest candidates)))))))


#_(defn primes
  "Returns a lazy infinite sequence of primes"
  [] (letfn
       [(prime? [n primes-so-far] ; Faster check for primeness as we should have all lower primes in this case.
                (let [upper-bound (inc (Math/floor(Math/sqrt n)))]
                  (->> primes-so-far
                       map #(divides? n %))))
        (next-prime [primes-so-far next-num]
                    (->> (iterate inc next-num)
             ))]))


;; Sequences of prime factors
#_(defn prime-factors
  "Generates a sequence of prime factors of n"
  [n] (loop [m n
             factors []
             possible-factors (primes)]
        (if (<= m 1)
          factors
          (let [possible-factor (first possible-factors)]
           (if (divides? m possible-factor)
             (recur (quot m possible-factor) (conj factors possible-factor) possible-factors)
             (recur m factors (rest possible-factors)))))))

