(ns euler-clj.math.numbers-as-text)

(defn palindromic?
  "Returns whether a number is palindromic"
  [n] (let [str-version (str n)]
        (= str-version (apply str (reverse str-version)))))
