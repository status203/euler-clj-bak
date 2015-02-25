(ns euler-clj.math.numbers-as-text)

(defn palindromic?
  "Returns whether a number is palindromic"
  [n] (let [str-version (str n)]
        (= str-version (apply str (reverse str-version)))))

(defn digits
  "Returns a sequence of the digits of n (as integers)"
  [n] (map #(Integer/parseInt (str %)) (str n)))
