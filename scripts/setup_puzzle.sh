#!/bin/zsh

today=$(date +"%d")
year=$(date +"%Y")

# get puzzle input
aoc_token=$(cat .aoc_token)
url=https://adventofcode.com/2023/day/$(date +"%-d")/input

curl -b session=$aoc_token $url | perl -pe "chomp if eof" > ../resources/$year/day_$today.txt

# create test case file
touch ../resources/$year/day_${today}_test.txt

# create solution file
src_file=../src/aoc/$year/day_$today.clj
touch $src_file
cat > $src_file << EOL
(ns aoc.$year.day-${today}
  (:require [aoc.utils :refer [read-file]]))

(defn part-1
  [input])

(defn part-2
  [input])

(defn -main
  []
  (let [input (read-file "$year/day_${today}_test.txt")]
    (println (part-1 input))
    (println (part-2 input))))
EOL

# create test file
test_file=../test/aoc/$year/day_${today}_test.clj
touch $test_file
cat > $test_file << EOL
(ns aoc.$year.day-${today}-test
  (:require [clojure.test :refer [deftest is]]
            [aoc.$year.day-${today} :refer [part-1 part-2]]
            [aoc.utils :refer [read-file]]))

(def input (read-file "$year/day_${today}_test.txt"))

(deftest part-1-test
  (let [solution (part-1 input)
        expected nil]
    (is (= solution expected))))

(deftest part-2-test
  (let [solution (part-2 input)
        expected nil]
    (is (= solution expected))))
EOL
