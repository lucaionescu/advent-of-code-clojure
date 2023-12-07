(ns aoc.2023.day-02
  (:require [aoc.utils :refer [read-file sum]]
            [clojure.string :as str]))

(defn- valid-game?
  [game]
  (let [max_cubes {:red 12 :green 13 :blue 14}]
    (->> game
         :sets
         (map (fn [x] (every? #(<= (% x) (% max_cubes)) (keys max_cubes))))
         (every? true?))))

(defn- get-power
  [sets]
  (let [colors (keys (first sets))]
    (->> colors
         (map (fn [c] (map c sets)))
         (map #(apply max %))
         (apply *))))

(defn- parse-set
  [game_set]
  (let [[_ r] (re-find #"(\d+) red" game_set)
        [_ g] (re-find #"(\d+) green" game_set)
        [_ b] (re-find #"(\d+) blue" game_set)
        default-value (fnil read-string "0")]
    {:red (default-value r)
     :green (default-value g)
     :blue (default-value b)}))

(defn- parse-game
  [game_string]
  (let [[_ game_id games] (re-find #"^Game (\d+): (.*)" game_string)
        game_id (read-string game_id)
        sets (str/split games #";")
        sets (->> sets
                  (map str/trim)
                  (map parse-set))]
    {:id game_id :sets sets}))

(defn part-1
  [input]
  (->> input
       (map parse-game)
       (filter valid-game?)
       (map :id)
       sum))

(defn part-2
  [input]
  (->> input
       (map parse-game)
       (map :sets)
       (map get-power)
       sum))

(defn -main
  []
  (let [input (read-file "2023/day_02.txt")]
    (println (part-1 input))
    (println (part-2 input))))
