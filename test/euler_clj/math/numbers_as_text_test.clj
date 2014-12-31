(ns euler-clj.math.numbers-as-text-test
  (:require [midje.sweet :refer [tabular fact facts]]
            [euler-clj.math.numbers-as-text :refer :all]))

(tabular
 (facts "about whether numbers are palindromic"
        (palindromic? ?n) => ?result)

 ?n   ?result
 0    true
 1    true
 10   false
 404  true
 347  false
 12345678987654321 true)