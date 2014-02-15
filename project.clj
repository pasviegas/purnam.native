(defproject im.chit/purnam.native "0.4.0"
  :description "Native Javascript Methods"
  :url "http://www.github.com/purnam/purnam.native"
  :license {:name "The MIT License"
            :url "http://opensource.org/licencses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[org.clojure/clojurescript "0.0-2138"]
                                  [midje "1.6.0"]]
                   :plugins [[lein-cljsbuild "1.0.0"]]}}

  :cljsbuild {:builds [{:source-paths ["src"]
                       :compiler {:output-to "test/purnam.native.js"
                                  :optimizations :whitespace
                                  :pretty-print true}}]
             :test-commands {"unit-tests" ["phantomjs" :runner
                                           "this.literal_js_was_evaluated=true"
                                           "target/cljs/testable.js"]}})