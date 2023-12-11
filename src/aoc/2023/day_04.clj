(ns aoc.2023.day-04
  (:require [aoc.utils :refer [read-file sum]]
            [clojure.string :as str]))

(defn score
  [card]
  (let [card (str/replace-first card #".*:\s+" "")]
    (->> card
         (re-seq #"(?=\b(\d+)\b.*\|.*\b\1\b)")
         count)))

(defn part-1
  [input]
  (->> input
       (map score)
       (filter pos?)
       (map #(int (Math/pow 2 (dec %))))
       sum))

(defn part-2
  [input]
  (->> input
       (map score)
       (map #(vector %1 %2) (range (count input)))
       (reduce (fn [acc [i_card card_value]]
                 (vec (map-indexed (fn [i_acc acc_value]
                                     (if (< i_card i_acc (+ i_card card_value 1))
                                       (+ acc_value (acc i_card))
                                       acc_value))
                                   acc)))
               (vec (repeat (count input) 1)))
       sum))

(defn -main
  []
  (let [input (read-file "2023/day_04.txt")]
    (println (part-1 input))
    (println (part-2 input))))
