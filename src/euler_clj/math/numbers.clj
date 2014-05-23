(ns euler-clj.math.numbers)

(defn divides?
  "Whether integer n divides integer m without remainder"
  [m n] (= 0 (mod m n)))

(defn fib
  "Creates an infinite sequence of Fibonacci numbers starting with m and n"
  [m n] (cons m
              (lazy-seq (fib n (+' m n)))))


;; Prime predicates
(defn prime?
  "Checks whether n is prime"
  [n] (if (< n 2)
        false
        (->> (range 2 (inc (Math/floor(Math/sqrt n))))
             (filter #(divides? n %))
             empty?)))

;; Sequences of primes
(defn- primes-naive
 "Generates an infinite sequence of prime numbers by checking each number for primeness"
 [] (filter prime-naive? (range)))


(defn primes
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
(defn prime-factors
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

