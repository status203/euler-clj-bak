;;19. How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

(ns p19)

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

(count (filter #(= (% :weekday) 6)
               (take-while #(< (% :year) 2001)
                           (filter #(> (% :year) 1900)
                                   (first-in-months {:year 1900 :month 0 :weekday 0})))))

