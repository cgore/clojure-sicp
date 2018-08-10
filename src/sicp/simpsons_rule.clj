(ns sicp.simpsons-rule)

;; <http://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book-Z-H-12.html#%_sec_1.3>

;; Exercise 1.29.

;; Simpson's Rule is a more accurate method of numerical integration than the
;; method illustrated above. Using Simpson's Rule, the integral of a function f
;; between a and b is approximated as

;; h/3 [y0 + 4 y1 + 2 y2 + 4 y3 + 2 y4 + ... + 2 y(n-1) + 4 y(n-1) + yn]

;; where h = (b - a)/n, for some even integer n, and yk = f(a + kh). (Increasing
;; n increases the accuracy of the approximation.) Define a procedure that takes
;; as arguments f, a, b, and n and returns the value of the integral, computed
;; using Simpson's Rule. Use your procedure to integrate cube between 0 and
;; 1 (with n = 100 and n = 1000), and compare the results to those of the
;; integral procedure shown above.

(defn coeff [k n]
  (cond (= 0   k) 1
        (= n   k) 1
        (odd?  k) 4
        (even? k) 2))

(defn coeffs [n]
  (map #(coeff % (dec n)) (range n)))

(defn simpson
  [f a b n]
  (let [h (/ (- b a)
             n)
        yk (fn [k]
             (f (+ a (* k h))))]
    (* (/ h 3)
       (reduce + (map *
                      (coeffs n)
                      (map yk (range n)))))))

(defn cube [x]
  (* x x x))

(float (simpson cube 0 1 100000))
