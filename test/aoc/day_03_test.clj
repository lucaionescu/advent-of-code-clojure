(ns aoc.day-03-test
  (:require [clojure.test :refer [deftest is are]]
            [aoc.day-03 :refer [common-item part-1 part-2 priority]]))

(deftest priority-test
  (are [x y] (= x y)
    (priority \a) 1
    (priority \b) 2
    (priority \z) 26
    (priority \A) 27
    (priority \B) 28
    (priority \Z) 52))

(deftest common-item-test
  (is (= (common-item [#{1 2} #{2 3} #{2 4}]) 2)))

(def x ["vJrwpWtwJgWrhcsFMMfFFhFp"
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
        "PmmdzqPrVvPwwTWBwg"
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
        "ttgJtRGJQctTZtZT"
        "CrZsJsPPZsGzwwsLwLmpwMDw"])

(deftest part-1-test
  (let [solution (part-1 x)
        expected 157]
    (is (= solution expected))))

(deftest part-2-test
  (let [solution (part-2 x)
        expected 70]
    (is (= solution expected))))
