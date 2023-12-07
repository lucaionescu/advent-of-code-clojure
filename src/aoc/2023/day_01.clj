(ns aoc.2023.day-01
  (:require [aoc.utils :refer [read-file sum]]
            [clojure.string :as str]))

(defn part-1
  [lines]
  (->> lines
       (map #(re-seq #"\d" %))
       (transduce
        (map #(read-string (str (first %) (last %))))
        +)))

(defn parse-number
  [x]
  (let [numbers {:one "1" :two "2" :three "3" :four "4" :five "5"
                 :six "6" :seven "7" :eight "8" :nine "9"}]
    (if-let [num ((keyword x) numbers)]
      num
      x)))

(defn part-2
  [lines]
  (->> lines
       (reduce (fn [acc val]
                 (conj acc
                       [(re-find #"one|two|three|four|five|six|seven|eight|nine|\d" val)
                        (str/reverse (re-find #"eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|\d" (str/reverse val)))]))
               [])
       (map #(map parse-number %))
       (map str/join)
       (map read-string)
       sum))

(defn -main
  []
  (let [lines (read-file "2023/day_01.txt")]
    (println (part-1 lines))
    (println (part-2 lines))))
