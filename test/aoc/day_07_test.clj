(ns aoc.day-07-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.day-07 :refer [part-1 part-2]]))

(def input ["cd /"
            "ls\ndir a\n14848514 b.txt\n8504156 c.dat\ndir d"
            "cd a"
            "ls\ndir e\n29116 f\n2557 g\n62596 h.lst"
            "cd e"
            "ls\n584 i"
            "cd .."
            "cd .."
            "cd d"
            "ls\n4060174 j\n8033020 d.log\n5626152 d.ext\n7214296 k"])

(deftest part-1-test
  (is (= (part-1 input) 95437)))

(deftest part-2-test
  (is (= (part-2 input) 24933642)))
