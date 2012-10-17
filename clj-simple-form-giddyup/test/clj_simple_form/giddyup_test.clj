(ns clj-simple-form.giddyup-test
  (:use [clj-simple-form.giddyup]
        [clojure.test])
  (:require [hiccup.form :as hiccup]))

(deftest test-with-form-scope
  (testing "with-form-scope"
    (with-form-scope :profile {} {}
      (is (= hiccup/*group* ["profile"])))))

(deftest test-with-nested-form-scope
  (testing "with-nested-form-scope"
    (with-form-scope :profile {} {}
      (with-nested-form-scope :address
        (is (= hiccup/*group* ["profile" "address"]))))))