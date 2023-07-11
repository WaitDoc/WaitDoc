let stompClient = null;
let fromId = 0;
let ChatMessageUl = null;


function getChatMessages() {
    console.log("fromId : " + fromId);
    fetch(`/inquiry/${hospitalInquiryId}/messages?fromId=${fromId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        }
    })
        .then(response => response.json())
        .then(body => {
            drawMessages(body);
            console.log(body);
        });
}

function drawMessages(messages) {
    if (messages.length > 0) {
        fromId = messages[messages.length - 1].message_id;
    }

    messages.forEach((message) => {

        const newItem = document.createElement("li");
        newItem.textContent = `${message.nickname} : ${message.content}`;

        ChatMessageUl.appendChild(newItem);
    });
    scrollToBottom();
}

function ChatWriteMessage(form) {

    form.content.value = form.content.value.trim();

    stompClient.send(`/app/inquiry/${hospitalInquiryId}/sendMessage`, {}, JSON.stringify({content: form.content.value}));

    form.content.focus();
    form.content.value = '';
}


function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    const headers = {
        'X-CSRF-TOKEN': token,
    };

    stompClient.connect(headers, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe(`/topic/inquiry/${hospitalInquiryId}`, function (data) {
            getChatMessages();
        });


    });
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
        console.log('Disconnected');
    }
}

document.addEventListener("DOMContentLoaded", function() {
    ChatMessageUl = document.querySelector('.chat__message-ul');
    getChatMessages();
    connect();
});

function scrollToBottom() {
    const chatMessages = document.querySelector('.chat-messages');
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

