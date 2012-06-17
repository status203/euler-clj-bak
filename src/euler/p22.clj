(ns euler.p22)

(defn read-names []
  (map #(re-find #"[A-Z]+" %) (clojure.string/split (slurp "src/euler/p22-names.txt") #",")))

(def letter-scores
  (apply hash-map (interleave "ABCDEFGHIJKLMNOPQRSTUVWXYZ" (range 1 27))))

(defn score-name [n]
  (apply + (map #(letter-scores %) n)))

(apply +
       (map-indexed #(* (inc %) %2)
                    (map score-name
                         (sort (read-names)))))