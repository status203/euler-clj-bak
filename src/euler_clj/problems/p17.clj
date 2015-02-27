(def unit-words (zipmap (range 10) [nil "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"]))
(def teen-words (zipmap (range 10 21) ["ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen" "eighteen" "nineteen"]))
(def ten-words  (zipmap (range 2 10) ["twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"]))
(def trio-words ["" "thousand" "million" "billion" "trillian"])

(defn up-to-two-digit-word
  [n]
  (if (zero? n)
    "zero"
    (let [units (mod n 10)
          tens (quot n 10)]
      (case tens
        0 (unit-words n)
        1 (teen-words n)
        (str (ten-words tens)
             (if-let [unit-word (unit-words units)]
               (str "-" unit-word)))))))

(defn up-to-three-digit-word
  [n]
  (let [two-digits (mod n 100)
        hundreds   (quot n 100)]
  (str (if-let [hundreds-word (unit-words hundreds)]
         (str hundreds-word " hundred"))
       (up-to-two-digit-word two-digits))))


(map up-to-three-digit-word (range 1000))

(map up-to-two-digit-word (range 100))





