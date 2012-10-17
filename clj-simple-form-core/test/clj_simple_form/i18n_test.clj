(ns clj-simple-form.i18n-test
  (:use [clojure.test]
        [clj-simple-form.i18n])
  (:require [taoensso.tower :as tower]
            [clj-simple-form.test-support]))

(deftest test-t
  (testing "t"
    (with-form-translation-scope :profile
      (testing "object specific translations"
        (are [type field expected] (= (t type field) expected)
             :labels :email "Email"
             :hints :email "Double-check your email address."
             :placeholders :email "Translation missing"))
      (testing "default translations"
        (is (= (t :labels :name) "Name"))))))
