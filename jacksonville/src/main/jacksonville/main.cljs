(ns jacksonville.main
  (:require [clojure.string :as string]
            [jacksonville.css :as css]
            [jacksonville.styles :as styles]
            [reagent.core :as r]
            [reagent.dom :as rdom]
            ["@kobbleio/javascript" :refer [createKobbleClient]]))


(def env (if (string/includes? js/origin "localhost")
           :dev :prod))
(def redirect-uri (if #_(= env :dev) false
                    (str js/origin "/callback")
                    "https://app.chattysun.com/callback"))
(def kobble-client-id "clx9kjsba0000v7s3f9voh7wy")
(def ^:export kobble (createKobbleClient #js {"clientId" kobble-client-id
                                              "domain" "https://chattysun.portal.kobble.io"
                                              "redirectUri" redirect-uri}))
(def current-user (atom nil))

(defn signup-button []
  [:span "signup button work"])

;; can get access token -> logged in -> send to app
;; cannot get token -> show landing page with CTA to login
(defn ^:dev/after-load init []
  #_(do
    (css/add-css (styles/generate-css))
    (rdom/render
     [signup-button]
     (.getElementById js/document "jacksonvilleRoot"))))

(comment


  (.loginWithRedirect kobble)

  (-> (.handleRedirectCallback kobble)
      (.then #(js/console.log "kobble redir " %))
      (.catch #(prn (js->clj %))))

  (.handleRedirectCallback kobble)



  (.then (.getUser kobble) #(reset! current-user (js->clj % :keywordize-keys true)))
  @current-user

  (-> kobble (.-acl) (.listQuotas) (.then js/console.log))

  (.openPortalPricing kobble)

  (aset js/window "kobble" kobble)

  (=
   "@@kobble.spajs@@::clx9kjsba0000v7s3f9voh7wy::@@access_token@@"

   (str "@@kobble.spajs@@::" kobble-client-id "::@@access_token@@"))

  (-> (.getItem js/localStorage (str "@@kobble.spajs@@::" kobble-client-id "::@@access_token@@"))
      (js/JSON.parse)
      (js->clj :keywordize-keys true))

  (-> (.getItem js/localStorage (str "@@kobble.spajs@@::" kobble-client-id "::@@id_token@@"))
      (js/JSON.parse)
      (js->clj :keywordize-keys true)
      :claims
      :__raw) ;; id token
  )
