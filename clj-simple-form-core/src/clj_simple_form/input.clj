(ns clj-simple-form.input
  (:use [clj-simple-form.form-scope :only [error-for value-for]]
        [clj-simple-form.util :only [html-fn key-value-pair]])
  (:require [clj-simple-form.fields :as f]
            [clj-simple-form.i18n :as i18n]))

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
  ([field args]
     (input field args 
            (f/check-box field args))))

(defn check-box-group-input
  "Returns a group of checkboxes inside a labeled wrapper."
  ([field options] (check-box-group-input field options {}))
  ([field options args]
     (apply input field args
            (f/check-box-group field options args))))

(defn drop-down-input
  "Returns a drop down inside a labeled wrapper."
  ([field options] (drop-down-input field options {}))
  ([field options args]
     (input field args
            (f/drop-down field options args))))

(defn email-field-input
  "Returns an email field inside a labeled wrapper."
  ([field] (email-field-input field {}))
  ([field args]
     (input field args
            (f/email-field field args))))

(defn file-upload-input
  "Returns a file upload field inside a labeled wrapper."
  ([field] (file-upload-input field {}))
  ([field args]
     (input field args
            (f/file-upload field args))))

(defn password-field-input
  "Returns a password field inside a labeled wrapper."
  ([field] (password-field-input field {}))
  ([field args]
     (input field args
            (f/password-field field args))))

(defn radio-button-group-input
  "Returns a group of radio buttons inside a labeled wrapper."
  ([field options] (radio-button-group-input field options {}))
  ([field options args]
     (apply input field args
            (f/radio-button-group field options args))))

(defn text-area-input
  "Returns a text area inside a labeled wrapper."
  ([field] (text-area-input field {}))
  ([field args]
     (input field args
            (f/text-area field args))))

(defn text-field-input
  "Returns a text field inside a labeled wrapper."
  ([field] (text-field-input field {}))
  ([field args]
     (input field args
            (f/text-field field args))))