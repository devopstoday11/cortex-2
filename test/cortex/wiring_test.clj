(ns cortex.wiring-test
  (:use [clojure.test])
  (:use [cortex.core])
  (:use [clojure.core.matrix :as m]))

(deftest test-function-module
  ;; simple module implementing inc function
  (let [m (function-module inc)]
    (is (= 2 (output (calc m 1))))
    (is (= [0] (shape (parameters m))))))

(deftest test-stack-module
  ;; simple 2-layer stack
  (let [m (function-module inc)
        st (stack-module [m m])]
    (is (= 3 (output (calc st 1)))))
  
  ;; empty stack
  (let [st (stack-module [])]
    (is (= 1 (output (calc st 1))))
    (is (= [0] (shape (parameters st))))))
