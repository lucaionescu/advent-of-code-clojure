(ns aoc.2022.day-07
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [split trim starts-with?]]))

(defn parse-input
  "Groups the input such that each line contains a command and its output."
  ([] (parse-input "2022/day_07.txt"))
  ([file]
   (-> file
       resource
       slurp
       (split #"\$ ")
       (->> (map trim)
            (filterv #(seq %))))))

(defn get-files
  "Creates a mapping from file to size for the output of ls."
  [line]
  (let [files (split line #"\n")
        files_to_size (->> files
                           next
                           (filter #(not (starts-with? % "dir")))
                           (map #(split % #" "))
                           (map #(let [[x y] %] [y (read-string x)]))
                           (into {}))
        total_size (->> files_to_size
                        (vals)
                        (reduce +))]
    {:files files_to_size :size total_size}))

(defn parse-filesystem
  "Parses the command history into a mapping from directory paths to lists of files."
  [history]
  (loop [cmd history current_path [] filesystem {}]
    (if (empty? cmd)
      filesystem
      (let [line (first cmd)
            cmd (subvec cmd 1)]
        (cond
          (starts-with? line "cd")
          (let [dest (subs line 3)]
            (cond
              (= dest "/") (recur cmd ["/"] filesystem)
              (= dest "..") (recur cmd (pop current_path) filesystem)
              :else (recur cmd (vec (conj current_path dest)) filesystem)))

          (starts-with? line "ls")
          (let [files_to_size (get-files line)
                filesystem (merge filesystem {current_path files_to_size})
                filesystem (loop [current_path current_path filesystem filesystem]
                             (let [path (vec (drop-last current_path))]
                               (if (empty? path)
                                 filesystem
                                 (recur
                                  path
                                  (update-in filesystem [path :size] + (files_to_size :size))))))]
            (recur cmd current_path filesystem)))))))

(defn part-1
  [x]
  (->> x
       parse-filesystem
       vals
       (map :size)
       (filter #(<= % 100000))
       (apply +)))

(defn part-2
  [x]
  (let [filesystem (parse-filesystem x)
        total_size (get-in filesystem [["/"] :size])
        needed_space (- 30000000 (- 70000000 total_size))]
    (->> filesystem
         vals
         (map :size)
         (filter #(>= % needed_space))
         sort
         first)))

(defn -main
  []
  (let [x (parse-input)]
    (println (time (part-1 x)))
    (println (time (part-2 x)))))
