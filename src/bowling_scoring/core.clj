(ns bowling-scoring.core)

(defn strike? [rolls]
  (= 10 (first rolls)))

(defn spare? [rolls]
  (and (<= 2 (count rolls))
       (= 10 (reduce + (take 2 rolls)))))

(defn drop-previews-roll [rolls]
  (if (or (strike? rolls)
          (spare? rolls))
    (drop 3 rolls)
    (drop 2 rolls)))

(defn group-frames [rolls]
  (let [frame (if (or (strike? rolls)
                     (spare? rolls))
               (take 3 rolls)
               (take 2 rolls))
        score (reduce + frame)]
    {:score score}))

(defn sum-frames [rolls]
  (loop [remains rolls
         frames []]
    (if (or (= 10 (count frames))
            (= 0 (count remains)))
      frames
      (recur (drop-previews-roll remains)
             (conj frames (group-frames remains))))))

(defn scorecard [rolls]
  (reduce (fn [acc score]
            (let [{:keys [total]} (last acc)]
              (->> ((fnil + 0) total
                     (:score score))
                   (assoc score :total)
                   (conj acc))))
          []
          (sum-frames rolls)))

