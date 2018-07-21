(ns rna-transcription)

;; nucleotide character mapping
(def rna->dna* { \G \C
                 \C \G
                 \T \A
                 \A \U })

;; nucleotide character mapping w/ assertion of failure
(defn rna->dna [x]
  (let [conversion (rna->dna* x)]
    (assert (not (nil? conversion)))
    (conversion)))

(defn to-rna [dna]
  (clojure.string/join (map rna->dna dna)))
