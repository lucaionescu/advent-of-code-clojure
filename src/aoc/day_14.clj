(ns aoc.day-14
  (:require [aoc.utils :refer [read-file]]
            [clojure.string :refer [split]]))

(defn points->line
  "Returns the points of a line given the start and end coordinates"
  [[[x_1 y_1] [x_2 y_2]]]
  (for [x (range (min x_1 x_2) (inc (max x_1 x_2)))
        y (range (min y_1 y_2) (inc (max y_1 y_2)))]
    [x y]))

(defn parse-input
  "Returns a set containing the coordinates of the rock paths."
  [rows]
  (->> rows
       (map #(split % #"( -> |,)"))
       (map (fn [x] (map read-string x)))
       (map #(partition 2 %))
       (map #(partition 2 1 %))
       (mapcat (fn [row] (mapcat points->line row)))
       (into #{})))

(defn part-1
  [filled_tiles bottom]
  (letfn [(pour-sand [filled_tiles bottom]
            (loop [x 500 y 0]
              (cond
                (= y bottom) false
                (nil? (filled_tiles [x (inc y)])) (recur x (inc y))
                (nil? (filled_tiles [(dec x) (inc y)])) (recur (dec x) (inc y))
                (nil? (filled_tiles [(inc x) (inc y)])) (recur (inc x) (inc y))
                :else (conj filled_tiles [x y]))))]
    (loop [filled_tiles filled_tiles
           sand_units 0]
      (if-let [filled_tiles (pour-sand filled_tiles bottom)]
        (recur filled_tiles (inc sand_units))
        sand_units))))

(defn part-2
  [filled_tiles bottom]
  (letfn [(pour-sand [filled_tiles bottom]
            (loop [x 500 y 0]
              (cond
                (= y bottom) (conj filled_tiles [x y])
                (nil? (filled_tiles [x (inc y)])) (recur x (inc y))
                (nil? (filled_tiles [(dec x) (inc y)])) (recur (dec x) (inc y))
                (nil? (filled_tiles [(inc x) (inc y)])) (recur (inc x) (inc y))
                (= y 0) false
                :else (conj filled_tiles [x y]))))]
    (loop [filled_tiles filled_tiles
           sand_units 1]
      (if-let [filled_tiles (pour-sand filled_tiles bottom)]
        (recur filled_tiles (inc sand_units))
        sand_units))))

(defn -main
  []
  (let [rows (read-file "day_14.txt")
        filled_tiles (parse-input rows)]
    (let [bottom (->> filled_tiles (map second) (apply max))]
      (time (println (part-1 filled_tiles bottom))))
    (let [bottom (->> filled_tiles (map second) (apply max) inc)]
      (time (println (part-2 filled_tiles bottom))))))
