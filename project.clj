(defproject euler-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.priority-map "0.0.5"]
                 [sofs "0.1.0-SNAPSHOT"]]
  :profiles     {:dev {:dependencies [[midje "1.6.3"]
                                      [criterium "0.4.3"]]}})

