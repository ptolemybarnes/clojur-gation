(ns spanish-grammar-clj.answer
  (:gen-class))

(defn answer [conjugations answers verb]
  (if (= verb (get conjugations (count answers)))
    [true (concat answers [(get conjugations (count answers))])]
    [false answers]
    )
  )
