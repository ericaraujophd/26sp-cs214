(ns grader
  (:require [clojure.test :refer :all]))

;; ============================================================
;; CS214 Lab 05 Autograder — Lambda Functions (Clojure)
;;
;; Usage:
;;   clj -M grader.clj path/to/student-submission.clj
;;
;; The student file should define the following top-level bindings:
;;   maxAbsVal, expr1b-1, expr1b-2, expr1b-3,
;;   squareSquare, negate, sumSquares,
;;   multiplierMaker, doubler, tripler
;; ============================================================

;; ---- Load student submission --------------------------------

(def student-file
  (if-let [f (first *command-line-args*)]
    f
    (do (println "Usage: clj -M grader.clj <student-file.clj>")
        (System/exit 1))))

(try
  (load-file student-file)
  (catch Exception e
    (println (str "ERROR loading student file: " (.getMessage e)))
    (println "Grading will continue, but missing definitions will be marked as errors.")))

;; ---- Helpers ------------------------------------------------

(defmacro safe-call
  "Resolve a symbol at runtime and call it; report test error if undefined."
  [sym & args]
  `(if-let [v# (resolve '~sym)]
     ((deref v#) ~@args)
     (throw (Exception. (str "Symbol not defined: " '~sym)))))

(defmacro safe-val
  "Resolve a symbol at runtime and return its value; error if undefined."
  [sym]
  `(if-let [v# (resolve '~sym)]
     (deref v#)
     (throw (Exception. (str "Symbol not defined: " '~sym)))))

;; ---- Exercise 1a: maxAbsVal ---------------------------------

(deftest test-1a-maxAbsVal
  (testing "Exercise 1a – maxAbsVal: maximum of absolute values of three numbers"
    (is (= 3  (safe-call maxAbsVal  1 -3  2))  "maxAbsVal with mixed signs")
    (is (= 7  (safe-call maxAbsVal -7  4 -2))  "maxAbsVal with larger negative")
    (is (= 0  (safe-call maxAbsVal  0  0  0))  "maxAbsVal with all zeros")
    (is (= 5  (safe-call maxAbsVal  5  5  5))  "maxAbsVal with equal positives")
    (is (= 9  (safe-call maxAbsVal -9 -8 -7))  "maxAbsVal with all negatives")
    (is (= 10 (safe-call maxAbsVal 10  1  2))  "maxAbsVal with max in first position")
    (is (= 10 (safe-call maxAbsVal  1 10  2))  "maxAbsVal with max in second position")
    (is (= 10 (safe-call maxAbsVal  1  2 10))  "maxAbsVal with max in third position")))

;; ---- Exercise 1b: Calling anonymous functions ---------------

(deftest test-1b-expr-bindings
  (testing "Exercise 1b – anonymous function call results are bound"
    (is (some? (resolve 'expr1b-1)) "expr1b-1 is defined")
    (is (some? (resolve 'expr1b-2)) "expr1b-2 is defined")
    (is (some? (resolve 'expr1b-3)) "expr1b-3 is defined")

    (is (number? (safe-val expr1b-1)) "expr1b-1 should be a number")
    (is (boolean? (safe-val expr1b-2)) "expr1b-2 should be a boolean")
    (is (= 17.2 (safe-val expr1b-3)) "expr1b-3 should equal 17.2")))

;; ---- Exercise 2a: squareSquare -----------------------------

(deftest test-2a-squareSquare
  (testing "Exercise 2a – squareSquare: (x^2)^2 = x^4"
    (is (= 16   (safe-call squareSquare  2))  "squareSquare of 2")
    (is (= 16   (safe-call squareSquare -2))  "squareSquare of -2")
    (is (= 81   (safe-call squareSquare  3))  "squareSquare of 3")
    (is (= 81   (safe-call squareSquare -3))  "squareSquare of -3")
    (is (= 0    (safe-call squareSquare  0))  "squareSquare of 0")
    (is (= 1    (safe-call squareSquare  1))  "squareSquare of 1")
    (is (= 1    (safe-call squareSquare -1))  "squareSquare of -1")
    (is (= 625  (safe-call squareSquare  5))  "squareSquare of 5")))

;; ---- Exercise 3a: negate -----------------------------------

(deftest test-3a-negate
  (testing "Exercise 3a – negate: negate every element using map"
    (is (= '(1 -2 3)     (safe-call negate [-1  2 -3]))  "negate mixed signs")
    (is (= '(-4 5 -6)    (safe-call negate [ 4 -5  6]))  "negate mixed signs (2)")
    (is (= '(0 0 0)      (safe-call negate [ 0  0  0]))  "negate zeros")
    (is (= '(-1 -2 -3)   (safe-call negate [ 1  2  3]))  "negate all positives")
    (is (= '(1 2 3)      (safe-call negate [-1 -2 -3]))  "negate all negatives")
    (is (= '()           (safe-call negate []))           "negate empty sequence")))

;; ---- Exercise 4a: sumSquares --------------------------------

(deftest test-4a-sumSquares
  (testing "Exercise 4a – sumSquares: sum of squares using map + reduce"
    (is (= 14  (safe-call sumSquares '(1 2 3)))           "sumSquares of (1 2 3)")
    (is (= 55  (safe-call sumSquares '(1 2 3 4 5)))       "sumSquares of (1 2 3 4 5)")
    (is (= 15  (safe-call sumSquares [-1 -2 -3 0 1]))     "sumSquares with negatives and zero")
    (is (= 0   (safe-call sumSquares [0 0 0]))            "sumSquares of all zeros")
    (is (= 1   (safe-call sumSquares [1]))                "sumSquares of single element")
    (is (= 100 (safe-call sumSquares [6 8]))              "sumSquares of (6 8)")))

;; ---- Exercise 5a: multiplierMaker ---------------------------

(deftest test-5a-multiplierMaker
  (testing "Exercise 5a – multiplierMaker: function factory returning closures"
    (is (fn? (safe-val multiplierMaker))          "multiplierMaker is a function")
    (is (fn? (safe-call multiplierMaker 2))       "multiplierMaker returns a function")
    (is (= 10  (safe-call doubler 5))             "doubler 5 => 10")
    (is (= -6  (safe-call doubler -3))            "doubler -3 => -6")
    (is (= 0   (safe-call doubler 0))             "doubler 0 => 0")
    (is (= 15  (safe-call tripler 5))             "tripler 5 => 15")
    (is (= -9  (safe-call tripler -3))            "tripler -3 => -9")
    (is (= 0   (safe-call tripler 0))             "tripler 0 => 0")
    (is (= 40  ((safe-call multiplierMaker 10) 4))  "multiplierMaker 10 applied to 4 => 40")
    (is (= 0   ((safe-call multiplierMaker 0) 99))  "multiplierMaker 0 applied to 99 => 0")))

;; ---- Runner -------------------------------------------------

(defn -main []
  (println "===== CS214 Lab 05 Autograder =====")
  (println (str "Grading file: " student-file))
  (println)
  (let [summary (run-tests 'grader)]
    (println)
    (println "===== Summary =====")
    (println (str "Tests run:  " (:test summary)))
    (println (str "Assertions: " (+ (:pass summary) (:fail summary) (:error summary))))
    (println (str "Passed:     " (:pass summary)))
    (println (str "Failed:     " (:fail summary)))
    (println (str "Errors:     " (:error summary)))
    (let [total-assertions (+ (:pass summary) (:fail summary) (:error summary))
          score (if (pos? total-assertions)
                  (int (* 100 (/ (:pass summary) total-assertions)))
                  0)]
      (println)
      (println (str "Score: " score "/100"))
      (System/exit (if (= score 100) 0 1)))))

(-main)
