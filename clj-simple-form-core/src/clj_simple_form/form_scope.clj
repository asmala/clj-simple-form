(ns clj-simple-form.form-scope
  (:require [clj-simple-form.i18n :as i18n]))

(def ^:dynamic *form-values* {})
(def ^:dynamic *form-errors* {})
(def ^:dynamic *error-ns* nil)

(defmacro with-form-scope
  "Sets up bindings for form values, errors, translations, and field names."
  [object values errors & content]
  `(->> (i18n/with-form-translation-scope ~object ~@content)
        (binding [*form-values* ~(or values {})
                  *form-errors* ~(or errors {})])))

(defmacro with-nested-form-scope
  "Sets up bindings for form values, errors, translations, and field names."
  [object & content]
  `(let [k# (keyword ~object)
         error-ns# (if *error-ns*
                     (conj *error-ns* k#)
                     [k#])]
     (->> (i18n/with-form-translation-scope ~object ~@content)
          (binding [*form-values* (or (k# *form-values*) {})
                    *error-ns* error-ns#]))))

(defn value-for
  "Returns the value for the field in the current form scope."
  [field]
  (*form-values* field))

(defn error-for
  "Returns the first error message, if any for the field."
  [field]
  (let [error-key (if *error-ns*
                    (conj *error-ns* field)
                    [field])
        errors (get-in *form-errors* error-key)]
    (if (or (sequential? errors)
            (set? errors))
      (first errors)
      errors)))
