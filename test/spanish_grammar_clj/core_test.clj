(ns spanish-grammar-clj.core-test
  (:require [clojure.test :refer :all]
            [spanish-grammar-clj.conjugate :refer :all]
            [spanish-grammar-clj.answer :refer :all]))

(deftest a-test

  (testing "Checking the correct answer"
    (def example-conjugations
      [ "paso" "pasas" "pasa" "pasamos" "pasáis" "pasan" ]
      )

    (is
      (=
       (answer example-conjugations [] "paso")
       [true ["paso"]]
       )
      )
    )

  (testing "Checking the incorrect answer"
    (def example-conjugations
      [ "paso" "pasas" "pasa" "pasamos" "pasáis" "pasan" ]
      )

    (is
      (=
       (answer example-conjugations [] "pasas")
       [false []]
       )
      )
    )

  (testing "testing preserves input answers"
    (def example-conjugations
      [ "paso" "pasas" "pasa" "pasamos" "pasáis" "pasan" ]
      )

    (is
      (=
       (answer example-conjugations ["paso"] "pasas")
       [true ["paso" "pasas"]]
       )
      )
    )

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
