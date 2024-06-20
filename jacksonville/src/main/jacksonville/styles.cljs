(ns jacksonville.styles
  (:refer-clojure :exclude [abs])
  (:require [garden.core :refer [css]]
            [garden.stylesheet :refer [rule at-media]]
            [garden.selectors :refer [selector descendant] :as sel]))

(def root-styles
  {:--gradient-color-rgb "200, 220, 240"
   :--gradient-color "rgba(var(--gradient-color-rgb), 1)"

   :--main-bg-color-rg "224, 247, 250"

   :--main-bg-colo "rgba(var(--main-bg-color-rgb), 1)"

   :--secondary-bg-color-rg "80, 162, 179"
   :--secondary-bg-color "rgba(var(--secondary-bg-color-rgb), 1)"

   :--feature-bg-color-rg "80, 162, 179"
   :--feature-bg-color "rgba(var(--feature-bg-color-rgb), 1)"

   :--main-text-color-rg "255, 255, 255"
   :--main-text-color "rgba(var(--main-text-color-rgb), 1)"})


(def base-styles
  [[sel/root root-styles]
   ["*" "*:before" "*:after" {:box-sizing "border-box"}]])

(def stylesheet
  (concat base-styles []))

(defn generate-css []
  (css stylesheet))
