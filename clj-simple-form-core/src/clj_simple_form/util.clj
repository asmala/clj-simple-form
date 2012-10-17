(ns clj-simple-form.util)

(def ^:private html-fns (atom {}))

(defn html-fn
  "Returns the HTML generation function corresponding to `component`."
  [component]
  (or (@html-fns component)
      (throw (Exception. (str "No HTML function found for " component)))))

(defn set-html-fns!
  "Overrides some or all of the functions used for generating HTML.

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
  value, returns that value as both the key and value."
  [option]
  (if (sequential? option)
    option
    [option option]))