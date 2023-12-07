(ns aoc.2023.day-01-test
  (:require [clojure.test :refer [deftest is are]]
            [aoc.2023.day-01 :refer [part-1 part-2 parse-number]]
            [aoc.utils :refer [read-file]]))

(deftest parse-number-test
  (are [x y] (= x y)
    (parse-number "one") "1"
    (parse-number "1") "1"))

(deftest part-1-test
  (let [lines (read-file "2023/day_01_test_01.txt")
        solution (part-1 lines)
        expected 142]
    (is (= solution expected))))

(deftest part-2-test
  (let [lines (read-file "2023/day_01_test_02.txt")
        solution (part-2 lines)
        expected 281]
    (is (= solution expected))))
