(ns euler.p2
  (:use euler.numbers))

 ;2. By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

(apply + (take-while (partial > 4000000) (filter even? (fib))))
