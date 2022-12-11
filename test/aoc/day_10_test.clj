(ns aoc.day-10-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.string :refer [join]]
            [aoc.day-10 :refer [part-1 part-2]]
            [aoc.utils :refer [read-file]]))

(def input (read-file "day_10_test.txt"))

(deftest part-1-test
  (is (= (part-1 input) 13140)))

(deftest part-2-test
  (is (= (part-2 input)
         (join "\n" ["##..##..##..##..##..##..##..##..##..##.."
                     "###...###...###...###...###...###...###."
                     "####....####....####....####....####...."
                     "#####.....#####.....#####.....#####....."
                     "######......######......######......####"
                     "#######.......#######.......#######....."]))))
