(ns rna-transcription)

;; nucleotide character mapping
(def nuc-subst { \G \C
                 \C \G
                 \T \A
                 \A \U })

;; nucleotide character mapping w/ assertion of failure
(defn rna->dna [x]
  (let [conversion (nuc-subst x)]
    (assert conversion)
    (conversion)))

(defn to-rna [dna]
  (clojure.string/join (map rna->dna dna)))
