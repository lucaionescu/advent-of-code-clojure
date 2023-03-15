(ns aoc.day-13-test
  (:require [clojure.test :refer [deftest is are]]
            [aoc.day-13 :refer [compare-packets parse-input part-1 part-2]]))

(def input
  [[1 1 3 1 1]
   [1 1 5 1 1]

   [[1] [2 3 4]]
   [[1] 4]

   [9]
   [[8 7 6]]

   [[4 4] 4 4]
   [[4 4] 4 4 4]

   [7 7 7 7]
   [7 7 7]

   []
   [3]

   [[[]]]
   [[]]

   [1 [2 [3 [4 [5 6 7]]]] 8 9]
   [1 [2 [3 [4 [5 6 0]]]] 8 9]])

(deftest parse-input-test
  (is (= input (parse-input "day_13_test.txt"))))

(deftest compare-packets-test
  (are [x] (= -1 x)
    (compare-packets [1 1 3 1 1] [1 1 5 1 1])
    (compare-packets [[1] [2 3 4]] [[1] 4])
    (compare-packets [[4 4] 4 4] [[4 4] 4 4 4])
    (compare-packets [] [3]))
  (are [x] (= 0 x)
    (compare-packets [] [])
    (compare-packets [1] [1]))
  (are [x] (= 1 x)
    (compare-packets [9] [[8 7 6]])
    (compare-packets [7 7 7 7] [7 7 7])
    (compare-packets [[[]]] [[]])
    (compare-packets [1 [2 [3 [4 [5 6 7]]]] 8 9] [1 [2 [3 [4 [5 6 0]]]] 8 9])))

(deftest part-1-test
  (is (= (part-1 input) 13)))

(deftest part-2-test
  (is (= (part-2 input) 140)))