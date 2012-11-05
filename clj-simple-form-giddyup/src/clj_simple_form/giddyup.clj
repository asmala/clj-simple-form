(ns clj-simple-form.giddyup
  "Scope functions for Hiccup interoperability. Requiring this namespace sets
  the form HTML functions to the contents of `giddyup.forms`."
  (:use [clj-simple-form.util :only [set-html-fns!]])
  (:require [giddyup.forms]
            [clj-simple-form.form-scope :as form-scope]
            [hiccup.form :as f]))

(set-html-fns! 'giddyup.forms)

(defmacro with-form-scope
  "Sets up bindings for form I18n, form values and errors, as well as Hiccup form
  elements.

  ### Example

      (with-form-scope :profile {:email \"joe@example.com\"} {}
        (email-field-input :email))"
  [object values errors & content]
  `(->> (f/with-group ~object ~@content)
        (form-scope/with-form-scope ~object ~values ~errors)))

(defmacro with-nested-form-scope
  "Sets up bindings for form I18n, form values and errors, as well as Hiccup form
  elements, using the current form scope as a basis.

  ### Example

      (with-form-scope :profile {} {:address {:street \"is required\"}}
        (with-nested-form-scope :address
          (text-field-input :street)))"
  [object & content]
  `(->> (f/with-group ~object ~@content)
        (form-scope/with-nested-form-scope ~object)))
