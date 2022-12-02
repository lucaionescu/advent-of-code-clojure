(ns aoc.day-01
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [blank? split-lines]]))

(defn read-file
  ([] (read-file "day_01.txt"))
  ([file]
   (->> file
        resource
        slurp
        split-lines
        (partition-by blank?)
        (remove #(= % '("")))
        (map #(map read-string %)))))

(defn calories-per-inventory
  [x]
  (map #(reduce + %) x))

(defn part-1
  "Return the highest number of calories carried by an elf."
  [x]
  (apply max (calories-per-inventory x)))

(defn part-2
  "Return the sum of calories carried by the top 3 elves carrying the most."
  [x]
  (->> x
       calories-per-inventory
       (sort >)
       (take 3)
       (reduce +)))

(defn -main
  []
  (let [x (read-file)]
    (println (part-1 x))
    (println (part-2 x))))