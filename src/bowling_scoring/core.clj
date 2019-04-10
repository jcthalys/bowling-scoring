(ns bowling-scoring.core)

;game
;10 frames
;strike? on the first try = 10 score sheet
;spare?  = sum of firstest 2 is 10
;

{:score-card [{:score 17
               :total 17}                                   ;strike? [10, 0 0] or [10 10 10]
              {:score 12                                    ;non strike and non spare [5 7]
               :total 29}
              {:score 17
               :total 46}]}                                 ;spare? [5, 5, 7]

(defn strike? [rolls]
  (= 10 (first rolls)))

(defn spare? [rolls]
  (and (<= 2 (count rolls))
       (= 10 (reduce + (take 2 rolls)))))