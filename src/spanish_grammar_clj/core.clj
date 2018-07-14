(ns spanish-grammar-clj.core
  (:gen-class))

(defn get-stem [infinitive]
  (subs infinitive 0 (- (count infinitive) 2))
  )

(defn join-strings [first, second]
  (str first second)
  )

(defmulti conjugate (fn [verb] (apply str (take-last 2 verb)) ))

(defmethod conjugate "ar" [infinitive]
  (map (partial join-strings (get-stem infinitive)) [ "o" "as" "a" "amos" "áis" "an" ])
  )

(defmethod conjugate "ir" [infinitive]
  (map (partial join-strings (get-stem infinitive)) [ "o" "es" "e" "imos" "ís" "en" ])
  )

(defmethod conjugate "er" [infinitive]
  (map (partial join-strings (get-stem infinitive)) [ "o" "es" "e" "emos" "éis" "en" ])
  )
