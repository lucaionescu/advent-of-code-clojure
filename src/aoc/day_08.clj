(ns aoc.day-08
  (:require [aoc.utils :refer [read-file]]))

(defn parse-input
  ([] (parse-input "day_08.txt"))
  ([file]
   (->> file
        read-file
        (mapv #(mapv (fn [c] (Character/digit c 10)) %)))))

(defn compute-visibility
  [grid]
  (let [height (count grid)
        width (count (get grid 0))]

    (->> (for [x (range width)
               y (range height)]
           (let [tree_size (get-in grid [y x])]
             (or
       ; check visibility from north
              (every? #(< (get-in grid [% x]) tree_size) (range 0 y))

       ; check visibility from south
              (every? #(< (get-in grid [% x]) tree_size) (range (inc y) height))

       ; check visibility from east
              (every? #(< (get-in grid [y %]) tree_size) (range (inc x) width))

       ; check visibility from west
              (every? #(< (get-in grid [y %]) tree_size) (range 0 x))

              false)))
         (filter true?)
         count)))

(defn count-trees
  [trees tree_size]
  (loop [trees trees count 0]
    (let [[first_tree & other_trees] trees]
      (cond
        (nil? first_tree) count
        (>= first_tree tree_size) (inc count)
        :else (recur other_trees (inc count))))))

(defn compute-scenic-score
  [grid]
  (let [height (count grid)
        width (count (get grid 0))]
    (for [x (range 1 (dec width))
          y (range 1 (dec height))]
      (let [tree_size (get-in grid [y x])
            [north south east west]
            [(map #(get-in grid [% x]) (range (dec y) -1 -1))
             (map #(get-in grid [% x]) (range (inc y) height))
             (map #(get-in grid [y %]) (range (inc x) width))
             (map #(get-in grid [y %]) (range (dec x) -1 -1))]]
        (apply * (map #(count-trees % tree_size) [north south east west]))))))

(defn part-1
  [x]
  (compute-visibility x))

(defn part-2
  [x]
  (->> x
       compute-scenic-score
       (apply max)))

(defn -main
  []
  (let [x (parse-input)]
    (println (time (part-1 x)))
    (println (time (part-2 x)))))
