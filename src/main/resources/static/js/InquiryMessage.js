function getChatMessages() {
    console.log("fromId : " + fromId);
    fetch(`/hospital/${hospitalId}/inquiry/${hospitalRoomId}?fromId=${fromId}`, {
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
function ChatWriteMessage(form) {

    form.content.value = form.content.value.trim();

    stompClient.send(`/app/chats/${chatRoomId}/sendMessage`, {}, JSON.stringify({content: form.content.value}));

    form.content.focus();
    form.content.value = '';
}