;;;; CS214 Lab 05: Lambda Functions
;;;; Put all of your solutions in this file.
;;;; Each exercise asks you to define a binding with a specific name.
;;;; Run this file with:  clj lab05.clj

;;; Exercise 1a: maxAbsVal
;;  Define an anonymous function that returns the maximum of the
;;  absolute values of three numbers, and bind it to maxAbsVal.

; (def maxAbsVal ...)


;;; Exercise 1b: Calling Anonymous Functions
;;  Call each of the three anonymous functions below with appropriate
;;  arguments, and bind the results to the given names.

; (def expr1b-1 ((fn [x y z] (+ x y z)) ...))
; (def expr1b-2 ((fn [x] (nil? x)) ...))
; (def expr1b-3 ((fn [] 17.2)))


;;; Exercise 2a: squareSquare
;;  Define an anonymous function that computes the square of the square
;;  of its argument (x^4), and bind it to squareSquare.

; (def squareSquare ...)


;;; Exercise 3a: negate
;;  Define a function that negates every element in a sequence.
;;  Use map with an anonymous function. No recursion or loops.

; (def negate ...)


;;; Exercise 4a: sumSquares
;;  Define a function that computes the sum of the squares of a sequence.
;;  Use map and reduce with anonymous functions. No recursion or loops.

; (def sumSquares ...)


;;; Exercise 5a: multiplierMaker
;;  Define a function factory that takes a number n and returns a new
;;  function that multiplies its argument by n.
;;  Then use it to create `doubler` and `tripler`.

; (def multiplierMaker ...)
; (def doubler  (multiplierMaker 2))
; (def tripler  (multiplierMaker 3))


;;;; ---- Testing ------------------------------------------------
;;;; Uncomment tests below as you complete each exercise.
;;;; Run with:  clj lab05.clj

(defn -main []
  (println "===== CS214 Lab 05 Tests =====")
  (println)

  ;; -- Exercise 1a --
  ; (println "--- Exercise 1a: maxAbsVal ---")
  ; (println "(maxAbsVal 1 -3 2)  =>" (maxAbsVal 1 -3 2)   "  expected: 3")
  ; (println "(maxAbsVal -7 4 -2) =>" (maxAbsVal -7 4 -2)   "  expected: 7")
  ; (println "(maxAbsVal 0 0 0)   =>" (maxAbsVal 0 0 0)     "  expected: 0")
  ; (println)

  ;; -- Exercise 1b --
  ; (println "--- Exercise 1b: Calling Anonymous Functions ---")
  ; (println "expr1b-1 =>" expr1b-1 "  (should be a number)")
  ; (println "expr1b-2 =>" expr1b-2 "  (should be a boolean)")
  ; (println "expr1b-3 =>" expr1b-3 "  expected: 17.2")
  ; (println)

  ;; -- Exercise 2a --
  ; (println "--- Exercise 2a: squareSquare ---")
  ; (println "(squareSquare 2)  =>" (squareSquare 2)   "  expected: 16")
  ; (println "(squareSquare -2) =>" (squareSquare -2)   "  expected: 16")
  ; (println "(squareSquare 3)  =>" (squareSquare 3)   "  expected: 81")
  ; (println "(squareSquare -3) =>" (squareSquare -3)   "  expected: 81")
  ; (println)

  ;; -- Exercise 3a --
  ; (println "--- Exercise 3a: negate ---")
  ; (println "(negate [-1 2 -3]) =>" (negate [-1 2 -3]) "  expected: (1 -2 3)")
  ; (println "(negate [4 -5 6])  =>" (negate [4 -5 6])  "  expected: (-4 5 -6)")
  ; (println)

  ;; -- Exercise 4a --
  ; (println "--- Exercise 4a: sumSquares ---")
  ; (println "(sumSquares '(1 2 3))       =>" (sumSquares '(1 2 3))         "  expected: 14")
  ; (println "(sumSquares '(1 2 3 4 5))   =>" (sumSquares '(1 2 3 4 5))     "  expected: 55")
  ; (println "(sumSquares [-1 -2 -3 0 1]) =>" (sumSquares [-1 -2 -3 0 1])   "  expected: 15")
  ; (println)

  ;; -- Exercise 5a --
  ; (println "--- Exercise 5a: multiplierMaker ---")
  ; (println "(doubler 5)              =>" (doubler 5)              "  expected: 10")
  ; (println "(doubler -3)             =>" (doubler -3)             "  expected: -6")
  ; (println "(tripler 5)              =>" (tripler 5)              "  expected: 15")
  ; (println "(tripler -3)             =>" (tripler -3)             "  expected: -9")
  ; (println "((multiplierMaker 10) 4) =>" ((multiplierMaker 10) 4) "  expected: 40")
  ; (println)

  (println "===== Done ====="))

(-main)
