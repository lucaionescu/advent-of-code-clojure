(ns aoc.day-12
  (:require [aoc.utils :refer [read-file]]
            [clojure.string :refer [index-of]]
            [clojure.data.priority-map :refer [priority-map]]))

(defn- char->elevation
  "Returns the elevation character for a given character or vertex position."
  ([char]
   (int (case char
          \S \a
          \E \z
          char)))
  ([vertex heightmap]
   (if-let [char (get-in heightmap [(second vertex) (first vertex)])]
     (char->elevation char)
     nil)))

(defn- parse-heightmap
  [rows]
  (mapv
   (fn [x]
     (->> x
          sequence
          (mapv char->elevation)))
   rows))

(defn find-position
  "Returns the coordinates of a position denoted by a character"
  [heightmap char]
  (loop [y 0]
    (if (= y (count heightmap))
      nil
      (if-let [x (index-of (nth heightmap y) char)]
        [x y]
        (recur (inc y))))))

(defn- find-positions
  "Returns the coordinates of all positions with a given elevation"
  [rows elevation]
  (loop [y 0 coordinates []]
    (if (= y (count rows))
      coordinates
      (let [row (rows y)]
        (recur
         (inc y)
         (into coordinates
               (reduce
                (fn [acc x]
                  (if (= elevation (char->elevation (.charAt row x)))
                    (conj acc [x y])
                    acc))
                []
                (range (count row)))))))))

(defn- init-distance-map
  "Initializes the default mapping from vertex to distance (inf)."
  [heightmap]
  (reduce-kv
   #(assoc %1 %3 Double/POSITIVE_INFINITY)
   {}
   (vec
    (mapcat
     (fn [y]
       (map
        (fn [x] [x y])
        (vec (range (count (heightmap y))))))
     (vec (range (count heightmap)))))))

(defn- get-neighbors
  "Returns a vector containing all reachable neighbors of the given vertex."
  [vertex heightmap]
  (let [vertex_elevation (char->elevation vertex heightmap)]
    (reduce
     (fn [acc step]
       (let [neighbor (mapv + vertex step)
             neighbor_elevation (char->elevation neighbor heightmap)]
         (cond
           (nil? neighbor_elevation) acc
           (>= vertex_elevation (dec neighbor_elevation)) (conj acc neighbor)
           :else acc)))
     []
     [[0 -1] [1 0] [0 1] [-1 0]])))

(defn- update-neighbors
  "Updates the distances for the given vertex."
  [vertex neighbors dist]
  (let [current_distance (inc (dist vertex))]
    (reduce
     (fn [acc neighbor]
       (if (< current_distance (dist neighbor))
         (assoc acc neighbor current_distance)
         acc))
     dist
     neighbors)))

(defn dijkstra
  "Uses Dijkstra's algorithm to compute a mapping of vertex -> distance to starting vertex."
  [heightmap start_pos]
  (let [dist (init-distance-map heightmap)
        dist (assoc dist start_pos 0)]
    (loop [queue (into (priority-map) dist)
           dist dist]
      (let [[vertex _] (peek queue)
            queue (pop queue)
            neighbors (get-neighbors vertex heightmap)
            neighbors (filter #(contains? queue %) neighbors)
            dist (update-neighbors vertex neighbors dist)]
        (if (empty? queue)
          dist
          (recur
           (reduce-kv (fn [acc k _] (assoc acc k (dist k))) queue queue)
           dist))))))

(defn part-1
  [rows end_pos]
  (let [heightmap (parse-heightmap rows)
        start_pos (find-position rows \S)]
    ((dijkstra heightmap start_pos) end_pos)))

(defn part-2
  [rows end_pos]
  (let [heightmap (parse-heightmap rows)
        start_positions (find-positions rows (char->elevation \a))]
    (->> start_positions
         (map #(dijkstra heightmap %))
         (map #(% end_pos))
         (apply min))))

(defn -main
  []
  (let [rows (read-file "day_12.txt")
        end_pos (find-position rows \E)]
    (time (println (part-1 rows end_pos)))
    (time (println (part-2 rows end_pos)))))
