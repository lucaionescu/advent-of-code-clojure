(ns aoc.day-15
  (:require [aoc.utils :refer [manhattan read-file sum]]))

(defn parse-input
  [filename]
  (->> filename
       read-file
       (map #(re-seq #"-?\d+" %))
       (map #(map read-string %))
       (mapv (fn [[s_x s_y b_x b_y]]
               {:sensor [s_x s_y]
                :beacon [b_x b_y]
                :manhattan (manhattan [s_x s_y] [b_x b_y])}))))

(defn exclude-positions
  [row {[s_x s_y] :sensor dist :manhattan}]
  (let [y_dist (abs (- row s_y))
        x_dist (- dist y_dist)]
    (when-not (neg? x_dist)
      [(- s_x x_dist) (+ s_x x_dist)])))

(defn filter-overlapping
  [slices]
  (reduce (fn [[[left_1 left_2] & results] [right_1 right_2]]
            (cond
              (nil? left_1) [[right_1 right_2]]
              (<= right_1 (inc left_2)) (conj results [left_1 (max left_2 right_2)])
              :else (conj results [left_1 left_2] [right_1 right_2])))
          []
          (sort slices)))

(defn tuning-frequency
  [x y]
  (+ y (* x 4000000)))

(defn part-1
  [input row]
  (->> input
       (map (partial exclude-positions row))
       (filter not-empty)
       filter-overlapping
       (map reverse)
       (map #(apply - %))
       sum))

(defn part-2
  [input rows]
  (loop [y 0]
    (let [slices (->> input
                      (map (partial exclude-positions y))
                      (filter not-empty)
                      filter-overlapping
                      (into []))]
      (cond
        (not (nil? (second slices))) (tuning-frequency (dec (get-in slices [0 0])) y)
        (= y (inc rows)) nil
        :else (recur (inc y))))))

(defn -main
  []
  (let [input (parse-input "day_15.txt")
        row 2000000]
    (time (println (part-1 input row)))
    (time (println (part-2 input 4000000)))))
