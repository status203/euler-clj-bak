(ns euler-clj.math.primes-test
  (:require [euler-clj.math.primes :refer :all]
            [midje.sweet :refer [tabular fact facts just]]
            [clojure.data.priority-map :refer [priority-map]]))

(tabular
 (facts "about primeness"
        (prime? ?n) => ?result)

 ?n  ?result
 0   false
 1   false
 2   true
 3   true
 4   false
 97  true
 125 false)

(fact "about first twenty primes (naively)"
 (take 20 (primes-naive)) => [ 2  3  5  7 11
                               13 17 19 23 29
                               31 37 41 43 47
                               53 59 61 67 71])

(tabular
  (facts "about finding the next prime"
         (prime-next ?n ?primes) => ?result)

 ?primes ?n ?result
 []      1  2)

(fact "about first twenty primes naively calculated"
 (take 20 (primes-naive)) => [ 2  3  5  7 11
                              13 17 19 23 29
                              31 37 41 43 47
                              53 59 61 67 71])

(fact "about first twenty primes calculated using previous primes"
 (take 20 (primes-by-prior-primes)) => [ 2  3  5  7 11
                                        13 17 19 23 29
                                        31 37 41 43 47
                                        53 59 61 67 71])

(tabular
 (facts "about prime factors"
        (prime-factors ?n) => ?result)

 ?n    ?result
  2    [2]
  4    [2 2]
  5    [5]
  6    [2 3]
 12    [2 2 3])

(tabular
 (facts "about updating sieve multiples for the next candidate"
        (sieve-counters-for-next-candidate ?sieve) => ?result)

 ?sieve                                   ?result
 [[2 3] [5 7 9] (priority-map 2 4 3 3)]  [[2 3] [5 7 9] {2 6, 3 6}])

#_(tabular
 (facts "about updating all sieve components for the next candidate"
        (sieve-next-candidate-update ?sieve) => ?result)

 ?sieve                                                         ?result
 [[2 3] (seq [5 7 9]) (priority-map 2 4 3 3)]                   [[2 3 5] (7 9) {5 5, 2 6, 3 6}]
 [[2 3 5] (seq [7 9 11 13]) (priority-map 2 6 3 6 5 5)]         [[2 3 5 7] (9 11 13) {7 7, 2 8, 3 9, 5 10}]
 [[2 3 5 7] (seq [9 11 13 15]) (priority-map 2 8 3 9 5 10 7 7)] [[2 3 5 7] (11 13 15) {3 9, 2 10, 5 10, 7 14}])

(fact "about first twenty primes calculated using iterative sieve"
 (take 20 (primes-by-iterative-sieve)) => [ 2  3  5  7 11
                                            13 17 19 23 29
                                            31 37 41 43 47
                                            53 59 61 67 71])

