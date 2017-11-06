(ns meetup-demo.db)

(def app-db
  {:account     nil
   :contract    {:name     "SimpleChat"
                 :abi      (.parse js/JSON "[{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"messages\",\"outputs\":[{\"name\":\"authorAddress\",\"type\":\"address\"},{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"imageHash\",\"type\":\"string\"},{\"name\":\"date\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"imageHash\",\"type\":\"string\"}],\"name\":\"addMessage\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"authorAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"text\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"imageHash\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"date\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"messageKey\",\"type\":\"uint256\"}],\"name\":\"onMessageAdded\",\"type\":\"event\"}]")
                 :address  "0xFde756E57734Abb5770539d553048992Aaba9E05"
                 :instance nil}
   :new-message {:text nil}
   :messages    []})