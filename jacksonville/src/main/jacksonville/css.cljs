(ns jacksonville.css
  (:require [goog.dom :as dom]))

(defn add-css [css-content]
  (let [head (.getElementsByTagName js/document "head")
        style-element (.createElement js/document "style")]
    (set! (.-type style-element) "text/css")
    (when (and (.-styleSheet style-element) (.styleSheet style-element))  ;; For IE
      (set! (.-cssText (.styleSheet style-element)) css-content))
    (do (.appendChild style-element (dom/createTextNode css-content))
        (.appendChild (aget head 0) style-element))))
