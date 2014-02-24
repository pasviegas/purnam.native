(ns midje-doc.purnam-native-functions
  (:use-macros [purnam.test :only [fact facts]]))

[[:chapter {:title "purnam.types" :tag "purnam-types"}]]

"Clojure protocols for javascript native objects and arrays."

[[:section {:title "init" :tag "init-types"}]]

"There is a dependency on [purnam.native](#purnam-cljs) and so the following MUST be placed in your project namespace in order to use this protocol"

(comment
  (:use [purnam.native :only [aget-in aset-in]])
  (:require [purnam.types :as types]))


  [[:section {:title "seq protocol"}]]

  (facts [[{:doc "seq protocol"}]]

    "The clojure `seq` protocol allow native js arrays and objects to be accessible like clojure vectors and maps:"

    (seq (js* "[1, 2, 3, 4]")) 
    => '(1 2 3 4)
  
    (seq (js* "{a:1, b:2}"))
    => '(["a" 1] ["b" 2]) 
  
    "As well as all the built-in functionality that come with it. Although the datastructure then becomes a clojurescript list."
  
    (map #(* 2 %) (js* "[1, 2, 3, 4]"))
    => '(2 4 6 8)
  
    (take 2 (js* "[1, 2, 3, 4]"))
    => '(1 2)
  
    (count (js* "[1, 2, 3, 4]"))
    => 4
  
    (get (js* "[1, 2, 3, 4]") "1")
    => 2
  
    (get-in (js* "{a:{b:{c:1}}}") (js* "['a', 'b', 'c']"))
    => 1)

  [[:section {:title "transient protocol"}]]

  (facts [[{:doc "transient protocol"}]]

    "The clojure transinet protocol allow native js objects arrays to be manipulated using transient methods"

    (let [o (js-obj)]
      (assoc! o :a 1)
      o) 
    => (js* "{a:1}")
  
    (let [o (js* "{a:1}")]
      (dissoc! o :a)
      o) 
    => (js-obj)
  
    (persistent! (js* "{a:1}"))
    => {:a 1}
    )

  [[:section {:title "collection protocol"}]]

  "The collection protocols allow native js objects arrays to use `conj`, `assoc` and `dissoc` methods."

  (fact
      (def o (js-obj))
      (def o1 (conj o [:a 1]))
      o => (js-obj)
      o1 => (js* "{a:1}"))    

  (facts [[{:doc "collection protocol"}]]
    "It works with both arrays and objects"
  
    (conj (js* "[1]") 2 3)
    => (js* "[1,2,3]")
  
    (conj (js* "{a:1}") [:b 2] [:c 3])
    => (js* "{a:1,b:2,c:3}")

    (assoc (js* "{a:1}") :b 2 :c 3)
    => (js* "{a:1,b:2,c:3}")

    (dissoc (js* "{a:1}") :a)
    => (js-obj))