(defproject clj-simple-form "0.1.3"
  :description "Library for generating form elements."
  :url "https://github.com/asmala/clj-simple-form"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[clj-simple-form/clj-simple-form-core "0.1.3"]
                 [clj-simple-form/clj-simple-form-giddyup "0.1.3"]
                 [org.clojure/clojure "1.3.0"]]
  :plugins [[codox "0.6.1"]
            [lein-sub "0.2.3"]]
  :codox {:sources ["clj-simple-form-core/src"
                    "clj-simple-form-giddyup/src"]}
  :sub ["clj-simple-form-core"
        "clj-simple-form-giddyup"])
