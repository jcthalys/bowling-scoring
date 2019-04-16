(ns bowling-scoring.core-test
  (:require [clojure.test :refer :all]
            [bowling-scoring.core :refer :all]))

(deftest strike?-test
  (testing "test a strike roll"
    (is (strike? [10]))
    (is (not (strike? [5 3])))))

(deftest spare?-test

  (testing "test a spare roll"
    (is (spare? [5 5]))
    (is (spare? [8 2])))

  (testing "test a invalid spare roll"
    (is (false? (spare? [5 3])))))

(deftest drop-previews-roll-test
  (testing "test drop previews spare frame rolls"
    (is (= [7 8 9]
          (drop-previews-roll [5 5 6 7 8 9]))))

  (testing "test drop previews strike frame rolls"
    (is (= [6 7 8 9]
           (drop-previews-roll [10 5 5 6 7 8 9]))))

  (testing "test drop previews normal frame rolls"
    (is (= [5 6 7 8 9]
           (drop-previews-roll [3 5 5 6 7 8 9])))))

(deftest sum-frames-test
  (testing "sum each score frame and build card"
    (is (= [{:score 10}
            {:score 12}
            {:score 17}]
           (sum-frames [10 0 0 5 7 5 5 7]))))

  (testing "sum 10 score frame and build score"
    (is (= [{:score 20}
            {:score 17}
            {:score 9}
            {:score 20}
            {:score 30}
            {:score 22}
            {:score 15}
            {:score 5}
            {:score 17}
            {:score 13}]
          (sum-frames [10 10 0 7 10 7 2 5 5 10 10 10 10 10 10 2 5 5 5 2 3 6 4 7 7 3 3]))))

  (testing "sum more then 10 score frame and build score"
    (is (= [{:score 20}
            {:score 17}
            {:score 9}
            {:score 20}
            {:score 30}
            {:score 22}
            {:score 15}
            {:score 5}
            {:score 17}
            {:score 13}]
           (sum-frames [10 10 0 7 10 7 2 5 5 10 10 10 10 10 10 2 5 5 5 2 3 6 4 7 7 3 3 5 5])))))

(deftest scorecard-test
  (testing "score game so far"
    (is (= [{:score 10, :total 10}
            {:score 12, :total 22}
            {:score 17, :total 39}]
           (scorecard [10 0 0 5 7 5 5 7])))))

(deftest scorecard-test
  (testing "score full game of 10 frames"
    (is (= [{:score 20, :total 20}
            {:score 17, :total 37}
            {:score 9, :total 46}
            {:score 20, :total 66}
            {:score 30, :total 96}
            {:score 22, :total 118}
            {:score 15, :total 133}
            {:score 5, :total 138}
            {:score 17, :total 155}
            {:score 13, :total 168}]
           (scorecard [10 10 0 7 10 7 2 5 5 10 10 10 10 10 10 2 5 5 5 2 3 6 4 7 7 3 3])))))