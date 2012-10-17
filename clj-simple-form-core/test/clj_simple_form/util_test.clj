(ns clj-simple-form.util-test
  (:use [clojure.test]
        [clj-simple-form.util])
  (:require [clj-simple-form.mock-html-ns]))

(deftest test-html-fns
  (testing "set-html-fns! and html-fn"
    (testing "Function map"
      (set-html-fns! {:hello-world (fn [] "Hello World!")})
      (is (= ((html-fn :hello-world)) "Hello World!"))
      (is (thrown? Exception (html-fn :goodbye-world))))
    (testing "Function namespace"
      (set-html-fns! 'clj-simple-form.mock-html-ns)
      (is (string? ((html-fn :hint) "My two cents")))
      (is (thrown? Exception (html-fn :dirty-secret))))))

(deftest test-key-value-pair
  (testing "key-value-paid"
    (testing "Scalar values"
      (is (= (key-value-pair "key") ["key" "key"])))
    (testing "Tuples"
      (is (= (key-value-pair ["key" "value"]) ["key" "value"])))))