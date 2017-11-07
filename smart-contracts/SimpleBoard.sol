pragma solidity ^0.4.6;

contract SimpleBoard2 {

    struct Message {
    address authorAddress;
    string text;
    string ipfsHash;
    uint date;
    }

    Message[] public messages;

    event onMessageAdded(address authorAddress, string text, string ipfsHash, uint date, uint messageKey);

    function addMessage(string text, string ipfsHash) {
        messages.push(Message(msg.sender, text, ipfsHash, now));
        onMessageAdded(msg.sender, text, ipfsHash, now, messages.length - 1);
    }
}