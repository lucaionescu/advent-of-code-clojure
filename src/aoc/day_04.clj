(ns aoc.day-04
  (:require [aoc.utils :refer [read-file]]))

(defn parse-input
  ([] (parse-input "day_04.txt"))
  ([x]
   (->> x
        read-file
        (map #(subvec (re-matches #"(\d+)-(\d+),(\d+)-(\d+)" %) 1))
        (map #(map read-string %))
        (map vec))))

(defn fully-contains?
  [x]
  (let [[left_1 left_2 right_1 right_2] x]
    (or
     (and (>= left_1 right_1) (<= left_2 right_2))
     (and (<= left_1 right_1) (>= left_2 right_2)))))

(defn overlaps?
  [x]
  (let [[left_1 left_2 right_1 right_2] x]
    (and
     (<= right_1 left_2)
     (<= left_1 right_2))))

(defn part-1
  [x]
  (->> x
       (map fully-contains?)
       (filter true?)
       count))

(defn part-2
  [x]
  (->> x
       (map overlaps?)
       (filter true?)
       count))

(defn -main
  []
  (let [x (parse-input)]
    (println (time (part-1 x)))
    (println (time (part-2 x)))))
