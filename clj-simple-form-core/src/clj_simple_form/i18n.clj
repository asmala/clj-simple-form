(ns clj-simple-form.i18n
  "Functions for translating form contents, such as labels."
  (:require [taoensso.tower :as tower]))

(def ^:dynamic *translation-scope* [])

(defmacro with-form-translation-scope
  "Sets up a binding for form element translations.

  ### Example

      (with-form-translation-scope :profile
        (t :labels :email))"
  [object & content]
  `(binding [*translation-scope* (conj *translation-scope* ~(name object))]
     (list ~@content)))

(defn- lookup-seq->ns-seq
  "Converts a sequence of nested field groupings into a sequence of
  dictionary namespaces.

  ### Example

      (lookup-seq->ns-seq [\"a\" \"b\"]) ;=> (\"a.b\" \"b\")"
  [lookup-seq]
  (if (empty? lookup-seq)
    nil
    (letfn [(deepen-ns [k ns-s]
              (str ns-s "." k))
            (add-ns-key [ns-seq k]
              (conj (map (partial deepen-ns k) ns-seq) k))]
      (reverse (reduce add-ns-key
                       [(first lookup-seq)]
                       (rest lookup-seq))))))

(defn- scoped-keys
  "Returns the keys to be used with Tower translations."
  [type field]
  (let [type (name type)
        field (name field)
        ns-seq (concat (lookup-seq->ns-seq *translation-scope*) ["defaults"])]
    (vec (map #(keyword (str "simple-form." % "." type) field) ns-seq))))

(defn- root-scoped-t
  "Returns a translation igoring the current Tower translation scope."
  [k-or-ks]
  (tower/with-scope nil
    (tower/t k-or-ks)))

(defn t
  "Returns a translation of the given type for the field.

  ### Examples

      (t :labels :email)
      (t :placeholder :name \"First Last\")"
  ([type field]
     (root-scoped-t (scoped-keys type field)))
  ([type field default]
     (root-scoped-t (conj (scoped-keys type field) default))))