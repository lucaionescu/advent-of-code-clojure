(ns aoc.2022.day-09-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.2022.day-09 :refer [part-1 part-2]]))

(def input-1
  (flatten [(repeat 4 "R")
            (repeat 4 "U")
            (repeat 3 "L")
            (repeat 1 "D")
            (repeat 4 "R")
            (repeat 1 "D")
            (repeat 5 "L")
            (repeat 2 "R")]))

(def input-2
  (flatten [(repeat 5  "R")
            (repeat 8  "U")
            (repeat 8  "L")
            (repeat 3  "D")
            (repeat 17 "R")
            (repeat 10 "D")
            (repeat 25 "L")
            (repeat 20 "U")]))

(deftest part-1-test
  (is (= (part-1 input-1) 13)))

(deftest part-2-test
  (is (= (part-2 input-1) 1))
  (is (= (part-2 input-2) 30)))
