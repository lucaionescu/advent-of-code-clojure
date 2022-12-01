(ns aoc.day-01
  (:gen-class)
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [blank? split-lines]]))

(defn read-file
  ([] (read-file "day_01.txt"))
  ([file]
   (map
    (partial map read-string)
    (remove
     #(= % '(""))
     (partition-by
      blank?
      (split-lines (slurp (resource file))))))))

(defn calories-per-inventory
  [x]
  (map
   #(reduce + %)
   x))

(defn part-1
  "Return the highest number of calories carried by an elf."
  [x]
  (apply
   max
   (calories-per-inventory x)))

(defn part-2
  "Return the sum of calories carried by the top 3 elves carrying the most."
  [x]
  (reduce
   +
   (take
    3
    (sort
     >
     (calories-per-inventory x)))))

(defn -main
  []
  (println (part-1 (read-file)))
  (println (part-2 (read-file))))
