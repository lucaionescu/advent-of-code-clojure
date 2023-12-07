(ns aoc.2022.day-09
  (:require [aoc.utils :refer [read-file]]
            [clojure.string :as str]))

(defn parse-input
  ([] (parse-input "2022/day_09.txt"))
  ([file]
   (->> file
        read-file
        (map #(str/split % #"\s"))
        (mapcat (fn [[x y]] (repeat (read-string y) x))))))

(defn sign
  [x]
  (cond
    (pos? x) 1
    (neg? x) -1
    :else 0))

(defn move-knot-pair
  [first second]
  (let [[first_x   first_y] first
        [second_x second_y] second
        x_dist (- first_x second_x)
        y_dist (- first_y second_y)]
    (if (and (<= (abs x_dist) 1) (<= (abs y_dist) 1))
      [second_x second_y]
      [(+ second_x (sign x_dist)) (+ second_y (sign y_dist))])))

(defn move-knots
  [dir knots]
  (let [[h_x h_y] (first knots)
        head_pos (case dir
                   "R" [(inc h_x) h_y]
                   "L" [(dec h_x) h_y]
                   "U" [h_x  (inc h_y)]
                   "D" [h_x  (dec h_y)])]
    (loop [knots knots pos [head_pos]]
      (if (= 1 (count knots))
        pos
        (recur
         (rest knots)
         (conj pos (move-knot-pair (first knots) (second knots))))))))

(defn solve
  [directions tail_length]
  (loop [knots (vec (repeat (inc tail_length) [0 0]))
         dir directions
         visited #{}]
    (if (empty? dir)
      (count visited)
      (let [knots (move-knots (first dir) knots)
            dir (rest dir)
            visited (conj visited (last knots))]
        (recur knots dir visited)))))

(defn part-1
  [directions]
  (solve directions 1))

(defn part-2
  [directions]
  (solve directions 9))

(defn -main
  []
  (let [directions (parse-input)]
    (println (time (part-1 directions)))
    (println (time (part-2 directions)))))
