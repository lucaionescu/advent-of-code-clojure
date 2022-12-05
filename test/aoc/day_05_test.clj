(ns aoc.day-05-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.day-05 :refer [part-1 part-2]]))

(def starting_stacks {1 '(\N \Z)
                      2 '(\D \C \M)
                      3 '(\P)})

(def instructions [[1 2 1]
                   [3 1 3]
                   [2 2 1]
                   [1 1 2]])

(deftest part-1-test
  (let [solution (part-1 starting_stacks instructions)
        expected "CMZ"]
    (is (= solution expected))))

(deftest part-2-test
  (let [solution (part-2 starting_stacks instructions)
        expected "MCD"]
    (is (= solution expected))))
