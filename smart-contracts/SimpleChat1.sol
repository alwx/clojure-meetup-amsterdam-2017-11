pragma solidity ^0.4.6;

contract SimpleChat1 {

    struct ChatMessage {
    address authorAddress;
    string text;
    uint date;
    }

    ChatMessage[] public messages;

    event onMessageAdded(address authorAddress, string text, uint date, uint messageKey);

    function addMessage(string text) {
        messages.push(ChatMessage(msg.sender, text, now));
        onMessageAdded(msg.sender, text, now, messages.length - 1);
    }
}