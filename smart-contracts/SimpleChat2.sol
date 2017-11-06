pragma solidity ^0.4.6;

contract SimpleChat2 {

    struct Message {
    address authorAddress;
    string text;
    string imageHash;
    uint date;
    }

    Message[] public messages;

    event onMessageAdded(address authorAddress, string text, string imageHash, uint date, uint messageKey);

    function addMessage(string text, string imageHash) {
        messages.push(ChatMessage(msg.sender, text, imageHash, now));
        onMessageAdded(msg.sender, text, imageHash, now, messages.length - 1);
    }
}