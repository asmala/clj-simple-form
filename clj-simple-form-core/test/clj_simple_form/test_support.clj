(ns clj-simple-form.test-support
  (:require [taoensso.tower :as tower]
            [clj-simple-form.util :as util]))

(def dictionary
  {:en {:missing "Translation missing"
        :simple-form {:profile {:labels {:email "Email"}
                                :hints {:email "Double-check your email address."}}
                      :defaults {:labels {:name "Name"}}}}})

(tower/set-config! [:dictionary] dictionary)
