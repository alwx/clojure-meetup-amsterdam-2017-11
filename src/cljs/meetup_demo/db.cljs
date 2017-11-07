(ns meetup-demo.db)

(def app-db
  {:account      nil
   :contract     {:name     "SimpleBoard"
                  :abi      (.parse js/JSON "[{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"messages\",\"outputs\":[{\"name\":\"authorAddress\",\"type\":\"address\"},{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"ipfsHash\",\"type\":\"string\"},{\"name\":\"date\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"ipfsHash\",\"type\":\"string\"}],\"name\":\"addMessage\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"authorAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"text\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"ipfsHash\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"date\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"messageKey\",\"type\":\"uint256\"}],\"name\":\"onMessageAdded\",\"type\":\"event\"}]")
                  :address  "0x27B0233671d6a7b56334dCD1feCE05b43eEF7864"
                  :instance nil}
   :ipfs         {:node (new js/Ipfs (clj->js {:repo "amsterdam-clojure-meetup-ipfs"}))}
   :new-message  {:text nil}
   :messages     []
   :ipfs-content {}})