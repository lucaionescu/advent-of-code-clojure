(ns aoc.2022.day-01-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2022.day-01 :refer [part-1 part-2]]))

(def x [[1000 2000 3000]
        [4000]
        [5000 6000]
        [7000 8000 9000]
        [10000]])

(deftest part-1-test
  (let [solution (part-1 x)
        expected 24000]
    (is (= solution expected))))

(deftest part-2-test
  (let [solution (part-2 x)
        expected 45000]
    (is (= solution expected))))
