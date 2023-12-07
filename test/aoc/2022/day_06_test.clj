(ns aoc.2022.day-06-test
  (:require [clojure.test :refer [deftest are]]
            [aoc.2022.day-06 :refer [part-1 part-2]]))

(deftest part-1-test
  (are [x y] (= x y)
    (part-1 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7
    (part-1 "bvwbjplbgvbhsrlpgdmjqwftvncz") 5
    (part-1 "nppdvjthqldpwncqszvftbrmjlhg") 6
    (part-1 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10
    (part-1 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11))

(deftest part-2-test
  (are [x y] (= x y)
    (part-2 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19
    (part-2 "bvwbjplbgvbhsrlpgdmjqwftvncz") 23
    (part-2 "nppdvjthqldpwncqszvftbrmjlhg") 23
    (part-2 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29
    (part-2 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26))
