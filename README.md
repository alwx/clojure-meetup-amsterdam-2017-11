# spaces


## Prerequisites

* Install [Metamask](https://metamask.io);
* Create account there and unlock it;

## Deploying a smart contract

* Use https://ethereum.github.io/browser-solidity/
* Compile the code there, then deploy using https://wallet.ethereum.org/

In future there will be an easy script for deployment.

## Running

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. 

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 
