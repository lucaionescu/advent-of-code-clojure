(ns aoc.day-14-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.day-14 :refer [parse-input part-1 part-2 points->line]]))

(def rows
  ["498,4 -> 498,6 -> 496,6"
   "503,4 -> 502,4 -> 502,9 -> 494,9"])

(def bottom
  (->> rows parse-input (map second) (apply max) inc))

(deftest points->line-test
  (is (= (points->line [[0 0] [3 0]]) '([0 0] [1 0] [2 0] [3 0]))))

(deftest parse-input-test
  (is (=
       (parse-input rows)
       #{[502 9] [494 9] [496 9] [503 4] [497 6] [498 5] [502 7] [495 9] [496 6] [500 9]
         [501 9] [502 8] [502 6] [498 9] [502 5] [499 9] [498 6] [497 9] [502 4] [498 4]})))

(deftest part-1-test
  (is (= (part-1 (parse-input rows) bottom) 24)))

(deftest part-2-test
  (is (= (part-2 (parse-input rows) bottom) 93)))