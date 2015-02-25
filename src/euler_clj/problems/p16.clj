(ns euler-clj.problems.p16)

(->> (reduce *' (repeat 1000 2))
     str
     (map #(Integer/parseInt (str %)))
     (reduce +))