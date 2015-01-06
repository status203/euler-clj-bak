(ns euler.p1)

                                        ;1. Find the sum of all the multiples of 3 or 5 below 100.
                                        ;=========================================================

(reduce +
        0
        (filter
         #(or
           (= (mod % 3) 0)
           (= (mod % 5) 0))
         (range 1 100)))
