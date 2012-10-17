(ns clj-simple-form.test-support
  (:require [taoensso.tower :as tower]
            [clj-simple-form.util :as util]))

(def dictionary
  {:en {:missing "Translation missing"
        :simple-form {:profile {:labels {:email "Email"}
                                :hints {:email "Double-check your email address."}}
                      :address {:labels {:street "Street"}}
                      :defaults {:labels {:name "Name"}}}}})

(tower/set-config! [:dictionary] dictionary)
