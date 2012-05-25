;;17. How many letters would be needed to write all the numbers in words from 1 to 1000?
 
(def *up-to-10* ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine"])
(def *up-to-20* ["ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen" "eighteen" "nineteen"])
(def *tens* ["twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"])
(def *trio-names* ["thousand" "million" "billion" "trillian"])

(def *letters* (into #{} "abcdefghijklmnopqrstuvwxyz"))

(defn chunk-in-words [x]
  "Convert an integer 0 < x < 1,000 into words"
  (let 
      [units (mod x 10)
       tens (mod (quot x 10) 10)
    hundreds (mod (quot x 100) 10)
    hundreds-text (if (zero? hundreds) "" (str (*up-to-10* (dec hundreds)) " hundred"))
    tens-text (if (>= tens 2) (*tens* (- tens 2)) "")
    units-ish-text (cond
                    (< tens 1) (if (zero? units) "" (*up-to-10* (dec units)))
                    (< tens 2) (*up-to-20* units)
                    :else (if (zero? units) "" (*up-to-10* (dec units))))]
  (str
   hundreds-text
   (if (and (not (= hundreds-text "")) (or (not (= tens-text "")) (not (= units-ish-text "")))) " and " "")
   tens-text
   (if (and (> tens 1) (> units 0)) "-")
   units-ish-text
   )))

(defn in-words [x]
  (let
      [chunk1 (mod x 1000)
       rest-chunks (quot x 1000)]
    (apply str
           (interpose ", " (filter
                            #(not= % "")
                            (concat
                             (loop [y rest-chunks, trio 0, acc ()] ; Get a list of the text for each chunk
                               (cond
                                (zero? y) acc
                                :else 
                                (let 
                                    [trio-value-in-words (chunk-in-words (mod y 1000))
                                     trio-name (*trio-names* trio)
                                     trio-in-words (if (= trio-name "") trio-value-in-words (str trio-value-in-words " " trio-name))]
                                  (recur (quot y 1000) (inc trio) (cons trio-in-words acc)))))
                             (let [chunk1-words (chunk-in-words chunk1)]
                               (list (if (and (> rest-chunks 0) (< 0 chunk1 100)) (str "and " chunk1-words) chunk1-words)))))))))

(defn letters-in-number-in-words [x]
  (reduce
   #(if (contains? *letters* %2) (inc %) %)
   0
   (in-words x)))

(println (apply + (map letters-in-number-in-words (range 1 1001))))

