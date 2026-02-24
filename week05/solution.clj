;;;; CS214 Lab 05: Lambda Functions — Solution Key

;;; Exercise 1a: maxAbsVal
(def maxAbsVal (fn [x y z] (max (Math/abs x) (Math/abs y) (Math/abs z))))

;;; Exercise 1b: Calling Anonymous Functions
(def expr1b-1 ((fn [x y z] (+ x y z)) 1 2 3))
(def expr1b-2 ((fn [x] (nil? x)) nil))
(def expr1b-3 ((fn [] 17.2)))

;;; Exercise 2a: squareSquare
(def squareSquare (fn [x] (* (* x x) (* x x))))

;;; Exercise 3a: negate
(def negate (fn [s] (map (fn [x] (- x)) s)))

;;; Exercise 4a: sumSquares
(def sumSquares (fn [s] (reduce (fn [a b] (+ a b)) (map (fn [x] (* x x)) s))))

;;; Exercise 5a: multiplierMaker
(def multiplierMaker (fn [n] (fn [x] (* n x))))
(def doubler  (multiplierMaker 2))
(def tripler  (multiplierMaker 3))
