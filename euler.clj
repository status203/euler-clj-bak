                                        ;21. Evaluate the sum of all the amicable numbers under 10000
                                        ;============================================================
                                        ;To avoid repeated calculations, looking at generating vector of sum of proper factors
(defn proper-factors [x] (disj (factors x) x))

(defn proper-factors-sum-to [x]
  (for [y (range 1 x)] [y (apply + (proper-factors y))]))

(defn amicable-numbers-sum-to [x]
  (let [pfst (into [] (proper-factors-sum-to x))]
    (apply +
           (map
            #(+ (first %) (second %))
            (filter
             #(and
               (< (first %) (second %))
               (< (second %) x)
               (= (first %) (second (get pfst (dec (second %))))))
             pfst)))))

                                        ;22. What is the total of all the name scores in the file?
                                        ;=========================================================
(println (into [] (.split (String/replace read-line "\"" "")) ","))



                                        ;23. Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
==========================================================================================================
(defn abundant? [x]
  (> (apply + (proper-factors x)) x))

(defn abundant-to [x]
  (filter abundant? (range x)))

(into #{} 
      (let [at (bundant-to 28123)]
        (for [x at, y at :while (<= y x)] (+ x y))))

                                        ;24. What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
===================================================================================================

(defn fct [x]
  (loop [y x, acc 1]
    (cond
     (< y 2) acc
     :else (recur (dec y) (* acc y)))))

(defn nth-lexicographic [chars n]
  "Returns the nth item from a lecixographic ordering of chars."
  (loop [chars (sort chars) n (dec n) s ""]
    (cond
     (empty? chars) s
     :else
     (let [first-char-loop (fct (dec (count chars)))
           first-char-index (quot n first-char-loop)
           first-char (nth chars first-char-index)
           second-char-steps (mod n first-char-loop)]
       (recur (filter #(not= first-char %) chars) second-char-steps (str s first-char))))))
