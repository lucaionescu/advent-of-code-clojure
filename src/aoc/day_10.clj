(ns aoc.day-10
  (:require [aoc.utils :refer [read-file]]
            [clojure.string :refer [join starts-with?]]))

(defn get-register
  [program]
  (->> program
       (mapcat #(cond
                  (= % "noop") [0]
                  (starts-with? % "addx") [0 (read-string (subs % 5))]))
       (reductions + 1)
       vec))

(defn part-1
  [program]
  (let [cycles [20 60 100 140 180 220]
        cycles_indices (map dec cycles)
        register (get-register program)]
    (reduce + (map * (map register cycles_indices) cycles))))

(defn part-2
  [program]
  (let [register (get-register program)]
    (->> register
         (map-indexed #(if (<= (abs (- (mod %1 40) %2)) 1) \# \.))
         (partition 40)
         (mapv #(apply str %))
         (join "\n"))))

(defn -main
  []
  (let [program (read-file "day_10.txt")]
    (println (part-1 program))
    (println (part-2 program))))
