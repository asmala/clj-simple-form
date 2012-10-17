(defproject clj-simple-form "0.1.1"
  :description "Library for generating form elements."
  :url "https://github.com/asmala/clj-simple-form"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[clj-simple-form/clj-simple-form-core "0.1.1"]
                 [clj-simple-form/clj-simple-form-giddyup "0.1.1"]]
  :plugins [[lein-sub "0.2.3"]]
  :sub ["clj-simple-form-core"
        "clj-simple-form-giddyup"])
