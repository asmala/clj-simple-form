(ns clj-simple-form.form-scope
  "Functions for managing scope for field errors and values."
  (:require [clj-simple-form.i18n :as i18n]))

(def ^:dynamic *form-values* {})
(def ^:dynamic *form-errors* {})
(def ^:dynamic *error-ns* [])

(defmacro with-form-scope
  "Sets up bindings for form values, errors, translations, and field names.

  ### Example

      (with-form-scope :profile {:email \"joe@example.com\"} {}
        (email-field-input :email))"
  [object values errors & content]
  `(->> (i18n/with-form-translation-scope ~object ~@content)
        (binding [*form-values* ~(or values {})
                  *form-errors* ~(or errors {})])))

(defmacro with-nested-form-scope
  "Sets up bindings for form values, errors, translations, and field names.

  ### Example

      (with-form-scope :profile {} {:address {:street \"is required\"}}
        (with-nested-form-scope :address
          (text-field-input :street)))"
  [object & content]
  `(let [k# (keyword ~object)]
     (->> (i18n/with-form-translation-scope ~object ~@content)
          (binding [*form-values* (or (*form-values* k#) {})
                    *error-ns* (conj *error-ns* k#)]))))

(defn value-for
  "Returns value for `field` in the current form scope.

  ### Example

      (value-for :email)"
  [field]
  (*form-values* field))

(defn error-for
  "Returns first error message, if any, for `field` in the current form scope.

  ### Example

      (error-for :email)"
  [field]
  (let [errors (get-in *form-errors* (conj *error-ns* field))]
    (if (or (sequential? errors)
            (set? errors))
      (first errors)
      errors)))
