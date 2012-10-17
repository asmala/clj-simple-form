(ns clj-simple-form.input
  (:use [clj-simple-form.form-scope :only [error-for value-for]]
        [clj-simple-form.util :only [html-fn key-value-pair]])
  (:require [clj-simple-form.i18n :as i18n]))

(defn input
  "Returns the input element(s) inside a labeled wrapper. Use for creating your
  own input functions."
  ([field] (input field nil))
  ([field input-or-args & inputs]
     (let [args (if (map? input-or-args) input-or-args {})
           inputs (if (map? input-or-args) inputs (cons input-or-args inputs))
           {:keys [error hint label label-html wrapper-html]} args
           wrapper-html (or wrapper-html {})
           error (or error (error-for field))
           hint (or hint (i18n/t :hint field nil))
           label (or label
                     (if (not (false? label)) (i18n/t :labels field)))
           label-html (or label-html {})]
       ((html-fn :wrapper) wrapper-html
              (if label
                ((html-fn :label) label-html field label))
              (if error ((html-fn :error) error))
              (if hint ((html-fn :hint) hint))
              inputs))))

(defn check-box-input
  "Returns a check box inside a labeled wrapper."
  ([field] (check-box-input field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     (input field args
            ((html-fn :check-box) input-html field value))))

(defn check-box-group-input
  "Returns a group of checkboxes inside a labeled wrapper."
  ([field options] (check-box-group-input field options {}))
  ([field options {:keys [value] :as args :or {value (value-for field)}}]
     (apply input field args
            (for [option options]
              (let [[k v] (key-value-pair option)
                    name (str (name field) "][")
                    checked? (contains? value v)]
                ((html-fn :group-check-box) name v k checked?))))))

(defn drop-down
  "Returns a drop down."
  ([field options] (drop-down field options {}))
  ([field options {:keys [input-html value] :as args
                   :or {input-html {} value (value-for field)}}]
     (let [placeholder (i18n/t :placeholders field nil)
           options (if placeholder
                     (cons [placeholder ""] options)
                     options)]
       ((html-fn :drop-down) input-html field options value))))

(defn drop-down-input
  "Returns a drop down inside a labeled wrapper."
  ([field options] (drop-down-input field options {}))
  ([field options args]
     (input field args (drop-down field options args))))

(defn email-field-input
  "Returns an email field inside a labeled wrapper."
  ([field] (email-field-input field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     (input field args
            (let [input-html (merge {:placeholder (i18n/t :placeholders field nil)}
                                    input-html)]
              ((html-fn :email-field) input-html field value)))))

(defn file-upload-input
  "Returns a file upload field inside a labeled wrapper."
  ([field] (file-upload-input field {}))
  ([field {:keys [input-html] :as args :or {input-html {}}}]
     (input field args
            ((html-fn :file-upload) input-html field))))

(defn password-field-input
  "Returns a password field inside a labeled wrapper."
  ([field] (password-field-input field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     (input field args
            ((html-fn :password-field) input-html field value))))

(defn radio-button-group-input
  "Returns a group of radio buttons inside a labeled wrapper."
  ([field options] (radio-button-group-input field options {}))
  ([field options {:keys [value] :as args :or {value (value-for field)}}]
     (apply input field args
            (for [option options]
              (let [[k v] (key-value-pair option)
                    checked? (= v value)]
                ((html-fn :group-radio-button) field v k checked?))))))

(defn text-area-input
  "Returns a text area inside a labeled wrapper."
  ([field] (text-area-input field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     (input field args
            ((html-fn :text-area) input-html field value))))

(defn text-field
  ([field] (text-field field {}))
  ([field {:keys [input-html value]
           :or {input-html {} value (value-for field)}}]
     (let [input-html (merge {:placeholder (i18n/t :placeholders field nil)}
                             input-html)]
       ((html-fn :text-field) input-html field value))))

(defn text-field-input
  "Returns a text field inside a labeled wrapper."
  ([field] (text-field-input field {}))
  ([field args]
     (input field args (text-field field args))))

(defn hidden-field
  ([field] (hidden-field field {}))
  ([field {:keys [value] :or {value (value-for field)}}]
     ((html-fn :hidden-field) field value)))