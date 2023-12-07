(ns aoc.2022.day-01
  (:require [aoc.utils :refer [read-file]]
            [clojure.string :refer [blank?]]))

(defn parse-input
  ([] (parse-input "2022/day_01.txt"))
  ([file]
   (->> file
        read-file
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
  (let [x (parse-input)]
    (println (part-1 x))
    (println (part-2 x))))