(ns aoc.day-02
  (:require [aoc.utils :refer [read-file]]))

(defn parse-input
  ([] (parse-input "day_02.txt"))
  ([file]
   (->> file
        read-file
        (map #(re-seq #"[ABCXYZ]" %)))))

(defn beats?
  [opp me]
  (let [map {:A :Z :B :X :C :Y}]
    (= me (map opp))))

(defn draws?
  [opp me]
  (let [map {:A :X :B :Y :C :Z}]
    (= me (map opp))))

(defn looses?
  [opp me]
  (let [map {:A :Y :B :Z :C :X}]
    (= me (map opp))))

(defn part-1
  [x]
  (->> x
       (map
        (fn [x]
          (let [opp (keyword (first x))
                me (keyword (second x))
                points ({:X 1 :Y 2 :Z 3} me)]
            (+ points
               (cond
                 (beats? opp me) 0
                 (draws? opp me) 3
                 (looses? opp me) 6)))))
       (reduce +)))

(defn part-2
  [x]
  (->> x
       (map (fn [x]
              (let [opp (keyword (first x))
                    me (keyword (second x))
                    loose_against {:A :Z :B :X :C :Y}
                    draw_against {:A :X :B :Y :C :Z}
                    win_against {:A :Y :B :Z :C :X}
                    points {:X 1 :Y 2 :Z 3}]
                (case me
                  :X (+ (points (loose_against opp)) 0)
                  :Y (+ (points (draw_against opp)) 3)
                  :Z (+ (points (win_against opp)) 6)))))
       (reduce +)))

(defn -main
  []
  (let [x (parse-input)]
    (println (time (part-1 x)))
    (println (time (part-2 x)))))
