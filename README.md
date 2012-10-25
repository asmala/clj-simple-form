Simple Form for Clojure [![Build Status](https://secure.travis-ci.org/asmala/clj-simple-form.png?branch=master)](http://travis-ci.org/asmala/clj-simple-form)
=======================

A library heavily inspired by the excellent Ruby library
[SimpleForm](https://github.com/plataformatec/simple_form), offering a
series of functions for quickly generating forms. 


## Installation

Add the following to your `project.clj`:

```clojure
[clj-simple-form "0.1.3"]
```

For other options, please refer to the library
[Clojars page](https://clojars.org/clj-simple-form).

This will include both the core libraries as well as a
[Hiccup](https://github.com/weavejester/hiccup) based HTML backend via
[Giddyup](https://github.com/asmala/giddyup). You can also include
only the core library and load your custom HTML backend by using
`clj-simple-form.util/set-html-fns!` and adding the following to your
`project.clj`:

```clojure
[clj-simple-form/clj-simple-form-core "0.1.3"]
```


## Documentation

You can find examples below and for more extensive documentation you
can refer to the [API docs](http://asmala.github.com/clj-simple-form).


## Quick start

```clojure
(def values {:name "Joe" :email "joe@example.com"})
(def errors {})

;; `clj-simple-form.form-scope` handles form bindings
(with-form-scope :profile values errors

  ;; `clj-simple-form.input` provides a set of ready form elements to use
  (text-field-input :name)
  (email-input :email)

  ;; `clj-simple-form.fields` allows you to use the value hook backend
  ;; with your own inputs
  (text-field :coupon-code)

  ;; `clj-simple-form.helpers` includes a few utility functions for forms
  [:div.form-actions
   (cancel-button "/")
   (submit-button)])
```

Internationalization is handled behind the scenes by
[Tower](https://github.com/ptaoussanis/tower). Simple Form looks for
specific keys in the dictionary to handle labels, hints, and
placeholders.

```clojure
(def dictionary
  {:en {:simple-form
        {:defaults {:actions {:cancel "Cancel"
                              :submit "Save"}
                    :labels {:email "Email"}}}
        {:profile {:actions {:submit "Save profile"}
                   :labels {:name "Name"}
                   :hints {:email "Your primary address"}
                   :placeholders {:name "First Last"}}}}})

(tower/set-config! :dictionary dictionary)
```


## Customizable HTML backends

By default, `clj-simple-form` will use a backend built around
[Hiccup](https://github.com/weavejester/hiccup). You can, however,
easily hook in another HTML generation library with
`clj-simple-form-core`. For now, please refer to the source of
`clj-simple-form-giddyup` for a reference implementation.


## Contributing

If you have suggestions for the library, you are welcome to open up a
[new issue](https://github.com/asmala/clj-simple-form/issues/new). I also
welcome code contributions, in which case I would recommend a
[pull request](https://help.github.com/articles/using-pull-requests)
with a feature branch.


## License

Copyright © 2012 Janne Asmala

Distributed under the
[Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html),
the same as Clojure.
