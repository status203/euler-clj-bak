;; 4. Find the largest palindrome made from the product of two 3-digit numbers.

(ns euler.p4
  (:use euler.text))

(defn n-digits [digits]
  (let
      [low (nth (iterate (partial * 10) 1) (dec digits))

       high (dec (nth (iterate (partial * 10) 1) digits))]
    (vector low high)))

(defn n-digit-products [digits]
  (let [[low high] (n-digits digits)]
    (distinct
     (for [x (range low (inc high)) y (range x (inc high))]
       (* x y)))))

(defn palindromic-products [digits]
  (filter palindromic? (n-digit-products digits)))

(last (sort (palindromic-products 3)))
