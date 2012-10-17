(ns clj-simple-form.helpers
  (:use [clj-simple-form.util :only [html-fn]])
  (:require [clj-simple-form.i18n :as i18n]))

(defn submit-button
  "Returns a submit-button with an automatic text."
  []
  ((html-fn :submit-button) (i18n/t :actions :submit)))

(defn cancel-button
  "Returns a cancel link formatted as a button."
  [url]
  ((html-fn :button-link-to) url (i18n/t :actions :cancel)))
