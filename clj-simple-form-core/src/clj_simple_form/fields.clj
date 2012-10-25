(ns clj-simple-form.fields
  "Functions for creating pre-populated form fields.

  ### Options for fields

  * `:input-html`: HTML attributes for the input element. Defaults to `{}`.
  * `:value`: Value for the field. Defaults to value for field in current scope."
  (:use [clj-simple-form.form-scope :only [error-for value-for]]
        [clj-simple-form.util :only [html-fn key-value-pair]])
  (:require [clj-simple-form.i18n :as i18n]))

(defn check-box
  "Returns a check box element.

  ### Example

      (check-box :remember-me)"
  ([field] (check-box field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     ((html-fn :check-box) input-html field value)))

(defn check-box-group
  "Returns a group of checkbox elements.

  ### Example

      (check-box-group :hobbies [[\"webdev\" \"Web Development\"]
                                 [\"spelunking\" \"Spelunking\"]])"
  ([field options] (check-box-group field options {}))
  ([field options {:keys [value] :as args :or {value (value-for field)}}]
     (for [option options]
       (let [[k v] (key-value-pair option)
             name (str (name field) "][")
             checked? (contains? value v)]
         ((html-fn :group-check-box) name v k checked?)))))

(defn drop-down
  "Returns a drop down.

  ### Example

      (drop-down :language [\"Finnish\" \"English\"])"
  ([field options] (drop-down field options {}))
  ([field options {:keys [input-html value] :as args
                   :or {input-html {} value (value-for field)}}]
     (let [placeholder (i18n/t :placeholders field nil)
           options (if placeholder
                     (cons [placeholder ""] options)
                     options)]
       ((html-fn :drop-down) input-html field options value))))

(defn email-field
  "Returns an email field element.

  ### Example

      (email-field :email {:value \"joe@example.com\"})"
  ([field] (email-field field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     (let [input-html (merge {:placeholder (i18n/t :placeholders field nil)}
                             input-html)]
       ((html-fn :email-field) input-html field value))))

(defn file-upload
  "Returns a file upload element.

  ### Example

      (file-upload :avatar)"
  ([field] (file-upload field {}))
  ([field {:keys [input-html] :as args :or {input-html {}}}]
     ((html-fn :file-upload) input-html field)))

(defn password-field
  "Returns a password field element.

  ### Example

      (password-field :password)"
  ([field] (password-field field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     ((html-fn :password-field) input-html field value)))

(defn radio-button-group
  "Returns a group of radio button elements.

  ### Example

      (radio-button-group :language [\"Finnish\" \"English\"]
                          {:value \"Finnish\"})"
  ([field options] (radio-button-group field options {}))
  ([field options {:keys [value] :as args :or {value (value-for field)}}]
     (for [option options]
       (let [[k v] (key-value-pair option)
             checked? (= v value)]
         ((html-fn :group-radio-button) field v k checked?)))))

(defn text-area
  "Returns a text area element.

  ### Example

      (text-area :bio {:value \"It was the summer of '69.\"})"
  ([field] (text-area field {}))
  ([field {:keys [input-html value] :as args
           :or {input-html {} value (value-for field)}}]
     ((html-fn :text-area) input-html field value)))

(defn text-field
  "Returns a text field element.

  ### Example

      (text-field :name {:input-html {:class \"name\"}})"
  ([field] (text-field field {}))
  ([field {:keys [input-html value]
           :or {input-html {} value (value-for field)}}]
     (let [input-html (merge {:placeholder (i18n/t :placeholders field nil)}
                             input-html)]
       ((html-fn :text-field) input-html field value))))

(defn hidden-field
  "Returns a hidden field element.

  ### Example

      (hidden-field :redirect-url {:value \"http://www.example.com/\"})"
  ([field] (hidden-field field {}))
  ([field {:keys [value] :or {value (value-for field)}}]
     ((html-fn :hidden-field) field value)))