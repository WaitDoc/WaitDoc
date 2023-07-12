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
        console.log(message);

        if (message.nickname === "상담사") {
            newItem.classList.add("counselor"); // 상담사인 경우 왼쪽 정렬
            newItem.textContent = `${message.nickname} : ${message.content}`;
        } else {
            newItem.classList.add("user"); // 상담사가 아닌 경우 오른쪽 정렬
            newItem.textContent = `${message.nickname} : ${message.content}`;
        }

        // const senderName = document.createElement("span");
        // senderName.classList.add("message-sender");
        // senderName.textContent = message.nickname;
        // newItem.appendChild(senderName);



        ChatMessageUl.appendChild(newItem);
    });

    scrollToBottom();
}

function ChatWriteMessage(form) {
    const content = form.content.value;

    // 메시지 전송 로직 추가

    form.content.value = ""; // 메시지 입력 필드 초기화
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

