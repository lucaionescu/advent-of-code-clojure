(ns aoc.day-11
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [split]]))

(defn- parse-monkey
  [paragraph]
  (let [lines          (split paragraph #"\n")
        items          (mapv read-string (split (subs (nth lines 1) 18) #", "))
        operation      (first (re-seq #"(\bold\b|\d+) (.) (\bold\b|\d+)" (nth lines 2)))
        operator       (nth operation 2)
        operand_1      (nth operation 1)
        operand_2      (nth operation 3)
        if_div         (read-string (re-find #"\d+" (nth lines 3)))
        if_true        (read-string (re-find #"\d+" (nth lines 4)))
        if_false       (read-string (re-find #"\d+" (nth lines 5)))]
    {:items items
     :operation (eval (read-string (format "(fn [old] (%s %s %s))" operator operand_1 operand_2)))
     :if_div if_div
     :test #(if (= 0 (mod % if_div)) if_true if_false)
     :inspections 0}))

(defn parse-input
  ([] (parse-input "day_11.txt"))
  ([file]
   (let [paragraphs (split (->> file resource slurp) #"\n\n")]
     (mapv parse-monkey paragraphs))))

(defn- throw-items
  [i monkeys relief_fun]
  (let [current_monkey (nth monkeys i)
        operation (current_monkey :operation)
        test (current_monkey :test)
        items (current_monkey :items)]
    (loop [items items
           monkeys monkeys]
      (if (empty? items)
        monkeys
        (let [[item & remaining_items] items
              worry_level (int (Math/floor (relief_fun (operation item))))
              throwing_to (test worry_level)
              monkeys (-> monkeys
                          (update-in [i :items] #(subvec % 1))
                          (update-in [throwing_to :items] #(conj % worry_level))
                          (update-in [i :inspections] inc))]
          (recur remaining_items monkeys))))))

(defn- play-round
  [monkeys relief_func]
  (loop [i 0
         monkeys monkeys]
    (if (= (count monkeys) i)
      monkeys
      (let [monkeys (throw-items i monkeys relief_func)]
        (recur (inc i) monkeys)))))

(defn- solve
  [monkeys rounds relief_func]
  (loop [rounds rounds
         monkeys monkeys]
    (if (zero? rounds)
      (->> monkeys
           (map :inspections)
           (sort >)
           (take 2)
           (apply *))
      (recur (dec rounds) (play-round monkeys relief_func)))))

(defn part-1
  [monkeys]
  (solve monkeys 20 #(/ % 3)))

(defn part-2
  [monkeys]
  (let [lcm (reduce * (map #(:if_div %) monkeys))]
    (solve monkeys 10000 #(mod % lcm))))

(defn -main
  []
  (let [monkeys (parse-input)]
    (time (println (part-1 monkeys)))
    (time (println (part-2 monkeys)))))
