(ns aoc.day-03
  (:require [aoc.utils :refer [read-file]]
            [clojure.set :refer [intersection]]))

(defn priority
  [x]
  (cond
    (Character/isLowerCase x) (- (int x) 96)
    (Character/isUpperCase x) (- (int x) 38)))

(defn common-item
  [x]
  (first (reduce intersection x)))

(defn process-line
  [x]
  (let [split (/ (count x) 2)]
    (map set (split-at split x))))

(defn part-1
  [x]
  (->> x
       (map process-line)
       (map common-item)
       (map priority)
       (reduce +)))

(defn part-2
  [x]
  (->> x
       (map set)
       (partition 3)
       (map common-item)
       (map priority)
       (reduce +)))

(defn -main
  []
  (let [x (read-file "day_03.txt")]
    (println (time (part-1 x)))
    (println (time (part-2 x)))))
