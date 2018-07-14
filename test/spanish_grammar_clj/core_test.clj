(ns spanish-grammar-clj.core-test
  (:require [clojure.test :refer :all]
            [spanish-grammar-clj.core :refer :all]))

(deftest a-test
  (testing "Conjugate ar-verbs"
    (is
      (=
       (conjugate "pasar")
       [
        "paso"
        "pasas"
        "pasa"
        "pasamos"
        "pasáis"
        "pasan"
        ]
       )
      )
    )
  (testing "Conjugate ir-verbs"
    (is
      (=
       (conjugate "vivir")
       [
        "vivo"
        "vives"
        "vive"
        "vivimos"
        "vivís"
        "viven"
        ]
       )
      )
    )

  (testing "Conjugate er-verbs"
    (is
      (=
       (conjugate "deber")
       [
        "debo"
        "debes"
        "debe"
        "debemos"
        "debéis"
        "deben"
        ]
       )
      )
    )
  )
