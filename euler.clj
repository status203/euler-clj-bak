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


