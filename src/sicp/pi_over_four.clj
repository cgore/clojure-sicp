(ns sicp.pi-over-four)

;; Exercise 1.31.  

;; a.  The sum procedure is only the simplest of a vast number of similar
;; abstractions that can be captured as higher-order procedures.^51 Write an
;; analogous procedure called product that returns the product of the values of
;; a function at points over a given range. Show how to define factorial in
;; terms of product. Also use product to compute approximations to [book-Z-G-D]
;; using the formula^52

;; pi/4 = (2 * 4 * 4 * 6 * 6 * 8 ...)/
;;        (3 * 3 * 5 * 5 * 7 * 7 ...)

;; b.  If your product procedure generates a recursive process, write one that
;; generates an iterative process. If it generates an iterative process, write
;; one that generates a recursive process.

(defn odds [n]
  (filter odd? (range n)))

(defn evens [n]
  (filter (every-pred even? pos?) (range n)))

(defn odds-but-one [n]
  (rest (odds n)))

(defn doubled-evens [n]
  (interleave (evens n)
              (evens n)))

(defn doubled-evens-over-one [n]
  (butlast (rest (doubled-evens n))))


(defn doubled-odds-but-one [n]
  (interleave (odds-but-one n)
              (odds-but-one n)))

(defn bottoms [n]
  (take n (doubled-odds-but-one (* 2 n))))

(defn bottom [n]
  (reduce * (bottoms n)))

(defn tops [n]
  (take n (doubled-evens-over-one (* n 2))))

(defn top [n]
  (reduce * (tops n)))

(defn pi-over-four [n]
  (/ (top n)
     (bottom n)))

(defn pi [n]
  (* 4 (pi-over-four n)))

(defn fpi [n]
  (float (pi n)))
