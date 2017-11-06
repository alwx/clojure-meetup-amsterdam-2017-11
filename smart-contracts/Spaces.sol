pragma solidity ^0.4.0;


// https://ethereum.meta.stackexchange.com/questions/443/blog-simple-storage-patterns-in-solidity

contract Spaces {

    struct EntityStruct {
    string storageHash;
    bool isEntity;
    }

    mapping (address => EntityStruct) public entityStructs;

    function isEntity(address entityAddress) public constant returns (bool isIndeed) {
        return entityStructs[entityAddress].isEntity;
    }

    function newEntity(address entityAddress, string storageHash) public returns (bool success) {
        if (isEntity(entityAddress)) throw;
        entityStructs[entityAddress].storageHash = storageHash;
        entityStructs[entityAddress].isEntity = true;
        return true;
    }

    function deleteEntity(address entityAddress) public returns (bool success) {
        if (!isEntity(entityAddress)) throw;
        entityStructs[entityAddress].isEntity = false;
        return true;
    }

    function updateEntity(address entityAddress, string storageHash) public returns (bool success) {
        if (!isEntity(entityAddress)) throw;
        entityStructs[entityAddress].storageHash = storageHash;
        return true;
    }
}