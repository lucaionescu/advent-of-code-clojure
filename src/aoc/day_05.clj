(ns aoc.day-05
  (:require [aoc.utils :refer [read-file]]
            [clojure.string :refer [blank?]]))

(defn parse-starting-stacks
  ([x]
   (->> x
        read-file
        (partition-by blank?)
        first
        drop-last
        (apply map vector)
        (partition-all 4)
        (map #(get (vec %) 1))
        (map (partial remove #(= \space %)))
        (zipmap (iterate inc 1)))))

(defn parse-instructions
  ([x]
   (->> x
        read-file
        (partition-by blank?)
        last
        (map #(subvec (re-matches #"^move (\d+) from (\d+) to (\d+)$" %) 1))
        (map #(map read-string %))
        (map vec))))

(defn rearrange
  [stacks instruction crate_mover]
  (let [[n_crates from to] instruction
        crates_to_move (take n_crates (stacks from))
        crates_to_move (if (= crate_mover 9000) (reverse crates_to_move) crates_to_move)]
    (-> stacks
        (update to #(concat crates_to_move %))
        (update from #(drop n_crates %)))))

(defn get-top-crates
  [stacks]
  (->> stacks
       sort
       vals
       (map first)
       (apply str)))

(defn part-1
  [stacks instructions]
  (get-top-crates (reduce #(rearrange %1 %2 9000) stacks instructions)))

(defn part-2
  [stacks instructions]
  (get-top-crates (reduce #(rearrange %1 %2 9001) stacks instructions)))

(defn -main
  []
  (let [input_file "day_05.txt"
        stacks (parse-starting-stacks input_file)
        instructions (parse-instructions input_file)]
    (println (time (part-1 stacks instructions)))
    (println (time (part-2 stacks instructions)))))
