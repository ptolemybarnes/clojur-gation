(ns spanish-grammar-clj.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.data.json :as json]
            [spanish-grammar-clj.conjugate :refer :all]
            [spanish-grammar-clj.answer :refer :all])
  )

(defn wrap-conjugation-in-map [infinitive]
  { infinitive (conjugate infinitive) }
  )

(def conjugate-infinitives (comp json/write-str (fn [args] (apply merge args)) (partial map wrap-conjugation-in-map) str/split-lines slurp))

(defn conjugate-infinitives-from-file []
  (print (conjugate-infinitives "./resources/regular_verbs.txt"))
  )

(def take-one (partial take 1))

(def pronouns ["yo" "tú" "él/ella/usted" "nosotros" "vosotros" "ustedes"])
(def blanks (repeat (count pronouns) "__________"))
(defn nth-safe [idx collection] (get (into-array collection) idx))

(defn present-pronouns-with [answers]
  (let [
        answers-or-nil (map-indexed (fn [idx nothing] (if (nth-safe idx answers) (nth-safe idx answers) nil)) (repeat (count pronouns) nil))
        ]
    (map (fn [pronoun answer blank] (str pronoun ": " (if (nil? answer) blank answer) "\n")) pronouns answers-or-nil blanks)
    )
  )

(defn cli "Read from STDIN" 
  ([verb-and-conjugations]
   (cli verb-and-conjugations [])
   )
  ([verb-and-conjugations answers]
  ; PRESENT
  ; for returns lazy seq so have to force evaluation with doall
  (def present (for [[infinitive conjugations] verb-and-conjugations]
    (println (str "Conjugate: " (name infinitive)))
    ))
  (doall present)
  (println "Enter conjugations:")
  (println (apply str (present-pronouns-with answers)))

  ; GET ANSWER
  (let [
        conjugations (second (first verb-and-conjugations))
        input (read-line)
        ]
    (let [
          response (answer conjugations answers input)
          is-correct (first response)
          new-answers (second response)
          ]
      (if is-correct (println "Correct!"))
      (cli verb-and-conjugations new-answers)
      )
    )
  ))

(def run (comp cli take-one seq json/read-json slurp))

(defn -main []
  (print (run "./resources/conjugations.json"))
  )

