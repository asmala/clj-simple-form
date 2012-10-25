(ns clj-simple-form.util
  "Utility functions for the `clj-simple-form` library.")

(def ^:private html-fns (atom {}))

(defn html-fn
  "Returns the HTML generation function corresponding to `component`.

  ### Example

      (html-fn :wrapper)"
  [component]
  (or (@html-fns component)
      (throw (Exception. (str "No HTML function found for " component)))))

(defn set-html-fns!
  "Overrides some or all of the functions used for generating HTML. If passed
  a namespace, will set all functions in the namespace as HTML functions based
  on their names.

  ### Examples

      (set-html-fns! {:hint mylib.html/hint})
      (set-html-fns! 'mylib.html)"
  [fmap-or-ns]
  (let [fmap (if (map? fmap-or-ns)
               fmap-or-ns
               (into {} (map #(vector (keyword (first %)) (second %))
                             (ns-publics fmap-or-ns))))]
    (reset! html-fns (merge @html-fns fmap))))

(defn key-value-pair
  "Returns a key-value pair based on the option. If the option is a scalar
  value, returns that value as both the key and value.

  ### Examples

      (key-value-pair \"Finnish\")
      (key-value-pair [\"fi\" \"Finnish])"
  [option]
  (if (sequential? option)
    option
    [option option]))