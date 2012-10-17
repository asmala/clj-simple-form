(ns clj-simple-form.input-test
  (:use [clojure.test]
        [clj-simple-form.input]
        [clj-simple-form.form-scope :only [with-form-scope]])
  (:require [clj-simple-form.test-support]))

;; SETUP

(def values {:name "Janne Asmala" :email "example.com"})
(def errors {:email "example is not a valid domain"})

;; TESTS