(ns clj-simple-form.form-scope-test
  (:use [clojure.test]
        [clj-simple-form.form-scope])
  (:require [clj-simple-form.i18n :as i18n]
            [clj-simple-form.test-support :as support]))

;; SETUP

(def values {:name "Janne Asmala" :email "example.com"})
(def errors {:email "example is not a valid domain"})

;; TESTS

(deftest test-with-form-scope
  (testing "with-form-scope"
    (with-form-scope :profile values errors
      (testing "i18n interoperability"
        (is (= (i18n/t :labels :email) "Email"))))))

(deftest test-value-for
  (testing "value-for"
    (with-form-scope :profile values errors
      (is (= (value-for :name) (values :name)))
      (is (nil? (value-for :phone))))))

(deftest test-error-for
  (testing "error-for"
    (with-form-scope :profile values errors
      (is (= (error-for :email) (errors :email)))
      (is (nil? (error-for :name))))))