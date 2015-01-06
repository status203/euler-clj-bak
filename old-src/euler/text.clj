(ns euler.text)

(defn palindromic?
  "Whether the supplied argument is palindromic"
  [x]
  (= (seq (str x)) (reverse (str x))))

