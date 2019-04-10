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


(deftest to-frames-test)