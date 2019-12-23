'use strict';

var usernamePageContainer = document.querySelector('.username-page-container');
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var userInfo = null;
var user_id = null;
var route_id = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect() {
	$(".connecting").css({"display": "inline-block"});
	$(".username-page-container").css({"display": "none"});
	$("#new-room-section").find("a").css({"color": "GREY", "cursor": "default"});
    if(!validate()) return false;
    if(username) {
        chatPage.classList.remove('hidden');    
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
}

function onConnected() {
    stompClient.subscribe(request_mapping_topic_public + "/" + route_id, onMessageReceived);    
    stompClient.send(request_mapping_app_chat_add_user + "/" + route_id,
        {},
        JSON.stringify({sender: username, type: 'JOIN', routeId: route_id, senderName: userInfo.fullName})
    )
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT',
            routeId: route_id,
            senderName: userInfo.fullName
        };
        stompClient.send(request_mapping_app_chat_send_message + "/" + route_id, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
	$(".connecting").css({"display": "none"});
	$("#rooms-header").css({"display": "inline-block"});
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');
	if(message.type) {
	    if(message.type === 'JOIN') {
	        messageElement.classList.add('event-message');
	        message.content = message.senderName + ' joined!';
	        afterUserJoined(message);
	    } else if (message.type === 'LEAVE') {
	        messageElement.classList.add('event-message');
	        message.content = message.senderName + ' left!';
	        afterUserLeft(message);
	    } else {
	        messageElement.classList.add('chat-message');
	        var avatarElement = document.createElement('i');
	        var avatarText = document.createTextNode(message.sender[0]);
	        avatarElement.appendChild(avatarText);
	        avatarElement.style['background-color'] = getAvatarColor(message.senderName);
	        messageElement.appendChild(avatarElement);
	        var usernameElement = document.createElement('span');
	        var usernameText = document.createTextNode(message.senderName);
	        usernameElement.appendChild(usernameText);
	        messageElement.appendChild(usernameElement);
	    }
	}else if(message.text){
		alert("response type : MessageWrapper");
	}
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

messageForm.addEventListener('submit', sendMessage, true);
