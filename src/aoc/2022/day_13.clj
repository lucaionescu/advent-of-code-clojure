(ns aoc.2022.day-13
  (:require [aoc.utils :refer [read-file sum]]))

(defn parse-input
  [filename]
  (->> filename
       read-file
       (filter seq)
       (map read-string)))

(defn compare-packets
  [left right]
  (cond
    (and (number? left)
         (number? right)) (compare left right)
    (number? left)        (compare-packets [left] right)
    (number? right)       (compare-packets left [right])
    :else                 (or
                           (->> (mapv compare-packets left right)
                                (drop-while zero?)
                                first)
                           (compare (count left) (count right)))))

(defn part-1
  [input]
  (->> input
       (partition 2)
       (map-indexed
        (fn [i x]
          (if (= -1 (compare-packets (first x) (second x))) (inc i) 0)))
       sum))

(defn part-2
  [input]
  (->> input
       (concat [[[2]]] [[[6]]])
       (sort compare-packets)
       (map-indexed (fn [i x] [(inc i) x]))
       (filter (fn [[_ x]] (or (= x [[2]]) (= x [[6]]))))
       (reduce (fn [acc [i _]] (* acc i)) 1)))

(defn -main
  []
  (let [input (parse-input "2022/day_13.txt")]
    (time (println (part-1 input)))
    (time (println (part-2 input)))))
