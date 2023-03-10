(defproject aoc "0.1.0-SNAPSHOT"
  :description "Advent of Code 2022"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/data.priority-map "1.1.0"]]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
