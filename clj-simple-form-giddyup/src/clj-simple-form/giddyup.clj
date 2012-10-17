(ns clj-simple-form.giddyup
  (:use [clj-simple-form.util :only [set-html-fns!]])
  (:require [giddyup.forms]
            [clj-simple-form.form-scope :as form-scope]
            [hiccup.form :as f]))

(set-html-fns! 'giddyup.forms)

(defmacro with-form-scope
  "Sets up bindings for form I18n, form values and errors, as well as Hiccup form
  elements."
  [object values errors & content]
  `(->> (f/with-group ~object ~@content)
        (form-scope/with-form-scope ~object)))

(defmacro with-nested-form-scope
  "Sets up bindings for form I18n, form values and errors, as well as Hiccup form
  elements, using the current form scope as a basis."
  [object & content]
  `(->> (f/with-group ~object ~@content)
        (form-scope/with-nested-form-scope ~object)))
