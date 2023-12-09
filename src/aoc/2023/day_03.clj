(ns aoc.2023.day-03
  (:require [aoc.utils :refer [char-is-digit? get-neighbors read-file sum]]
            [clojure.set :refer [intersection]]))

(defn- get-adjacent-fields
  [input symbols]
  (->> symbols
       (reduce (fn [acc coords]
                 (into acc (get-neighbors coords)))
               #{})
       (filter (fn [[x y]]
                 (int? (read-string (str (nth (nth input y "") x \x))))))
       set))

(defn- get-symbols
  [input regex]
  (->> input
       (map-indexed
        (fn [i item]
          (keep-indexed #(cond (re-find regex (str %2)) [%1 i]) item)))
       (filter seq)
       (mapcat identity)))

(defn get-numbers
  [input]
  (->> input
       (map (fn [line] (map-indexed vector line)))
       (map (fn [line]
              (partition-by #(char-is-digit? (second %)) line)))
       (map-indexed (fn [y groups]
                      (reduce (fn [acc group]
                                (if (char-is-digit? (second (first group)))
                                  (conj acc
                                        [(read-string (apply str (map second group)))
                                         (mapv #(vec [(first %) y]) group)])
                                  acc))
                              []
                              groups)))
       (mapcat identity)))

(defn- is-adjacent?
  [symbols [_ number_coordinates]]
  (let [number_coordinates (set number_coordinates)]
    (seq (intersection symbols number_coordinates))))

(defn part-1
  [input]
  (let [symbols (get-symbols input #"[^\d\.]")
        adjacent-fields (get-adjacent-fields input symbols)
        numbers (get-numbers input)]
    (->> numbers
         (filter #(is-adjacent? adjacent-fields %))
         (map first)
         sum)))

(defn part-2
  [input]
  (let [symbols (get-symbols input #"\*")
        numbers (get-numbers input)]
    (->> symbols
         (reduce (fn [acc i]
                   (let [neighbors (get-adjacent-fields input [i])]
                     (assoc acc i (map #(cond (is-adjacent? neighbors %) (first %)) numbers))))
                 {})
         (reduce (fn [acc [_ v]]
                   (let [v (filter int? v)]
                     (if (= (count v) 2)
                       (+ acc (apply * v))
                       acc)))
                 0))))

(defn -main
  []
  (let [input (read-file "2023/day_03.txt")]
    (println (part-1 input))
    (println (part-2 input))))
