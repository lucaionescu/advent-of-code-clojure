(ns aoc.2022.day-06
  (:require [aoc.utils :refer [read-file]]))

(defn parse-input
  ([] (parse-input "2022/day_06.txt"))
  ([x]
   (->> x
        read-file
        first)))

(defn all_different?
  [x]
  (= (count x) (count (set x))))

(defn process-message
  ([signal marker_size] (process-message signal marker_size marker_size))
  ([signal marker_size pos]
   (if (all_different? (take marker_size signal))
     pos
     (recur (apply str (drop 1 signal)) marker_size (inc pos)))))

(defn part-1
  ([signal]
   (process-message signal 4)))

(defn part-2
  ([signal]
   (process-message signal 14)))

(defn -main
  []
  (let [x (parse-input)]
    (println (time (part-1 x)))
    (println (time (part-2 x)))))
