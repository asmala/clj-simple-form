(ns clj-simple-form.helpers
  "Functions for generating miscellaneous form elements."
  (:use [clj-simple-form.util :only [html-fn]])
  (:require [clj-simple-form.i18n :as i18n]))

(defn submit-button
  "Returns a submit-button with an automatic text.

  ### Example

      (submit-button)"
  []
  ((html-fn :submit-button) (i18n/t :actions :submit)))

(defn cancel-button
  "Returns a cancel link to `url` formatted as a button.

  ### Example

      (cancel-button \"/journal-entries\")"
  [url]
  ((html-fn :button-link-to) url (i18n/t :actions :cancel)))
