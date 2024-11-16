"use strict";

var messageForm = document.querySelector('#messageForm');
var messageArea = document.querySelector('#messageArea');
var messageInput = document.querySelector('#messageInput');

var stompClient = null;

function connect() {
    var socket = new SockJS('/ws')
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);

    stompClient.send('/app/sendMessage', {}, "New user join");
}

function onMessageReceived(payload) {
    var message = payload.body;

    var messageElement = document.createElement('li');
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message)
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        stompClient.send('/app/sendMessage',
            {},
            messageContent);
    }
    event.preventDefault();
}

function onError() {
    console.log("Error connecting to socket");
}

connect()
messageForm.addEventListener('submit', sendMessage, true);