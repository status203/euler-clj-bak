                                        ;18 (& 67). Find the maximum total from top to bottom of the triangle
                                        ;==============================================================
(defn largest-sum-through-triangle [lines]
  (let
      [reversed-lines (reverse lines)]
    (loop [largest-line (first reversed-lines), remaining-lines (rest reversed-lines)]
      (cond
       (empty? remaining-lines) (max largest-line)
       :else
       (let
           [next-line (first remaining-lines)
            rest-lines (rest remaining-lines)
            mapped-line 
            (map
             #(+ % (max (first %2) (second %2)))
             next-line
             (partition 2 1 largest-line))]
         (recur mapped-line rest-lines))))))

(largest-sum-through-triangle 
 ['(75)
  '(95 64)
  '(17 47 82)
  '(18 35 87 10)
  '(20 4 82 47 65)
  '(19 1 23 75 3 34)
  '(88 2 77 73 7 63 67)
  '(99 65 4 28 6 16 70 92)
  '(41 41 26 56 83 40 80 70 33)
  '(41 48 72 33 47 32 37 16 94 29)
  '(53 71 44 65 25 43 91 52 97 51 14)
  '(70 11 33 28 77 73 17 78 39 68 17 57)
  '(91 71 52 38 17 14 91 43 58 50 27 29 48)
  '(63 66 4 68 89 53 67 30 73 16 69 87 40 31)

  '(4 62 98 27 23 9 70 98 73 93 38 53 60 4 23)])

(largest-sum-through-triangle 
 [])

                                        ;19. How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
                                        ;===============================================================================================================
(defn days-in-month [year month]
  (cond
   (#{4 6 9 11} (inc month)) 30
   (#{1 3 5 7 8 9 10 12} (inc month)) 31
   (zero? (mod year 400)) 29
   (zero? (mod year 100)) 28
   (zero? (mod year 4)) 29
   :else 28))

(defn first-in-next-month-weekday [{:keys [year month weekday]}]
  "Returns map of next year (zero-based) month and (zero-based Monday) weekday"
  (let 
      [days-in-cur-month (days-in-month year month)
       next-weekday (mod (+ weekday days-in-cur-month) 7)
       next-month (mod (inc month) 12)
       next-year (+ (if (= next-month 0) 1 0) year)]
    (hash-map :year next-year :month next-month :weekday next-weekday)))

(defn first-in-months [m]
  (let [next-first (first-in-next-month-weekday m)]
    (lazy-seq (cons m (first-in-months next-first)))))

(count (filter #(not= (% :weekday) 6) (take-while #(< (% :year) 2001) (filter #(> (% :year) 1900) (first-in-months {:year 1900 :month 0 :weekday 0})))))

                                        ;20. Find the sum of the digits in the number 100!
                                        ;=================================================
(defn fct [x]
  (loop [y x, acc 1]
    (cond
     (= y 1) acc
     :else (recur (dec y) (* acc y)))))

(sum-digits (fct 100M))

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
