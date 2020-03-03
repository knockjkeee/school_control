var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    // $("#userinfo").html("");
}

function connect() {
    // var socket = new WebSocket('ws://localhost:8091/ws')
    // var socket = new WebSocket('http://172.18.136.46/api/wsconnect')
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user', function (greeting) {
            // console.log(typeof greeting)
            var s = greeting.body;
            // console.log(typeof s)
            // console.log(greeting)
            view(greeting.body)
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    // stompClient.send("/app/user", {}, JSON.stringify({'name': $("#name").val()}));
}

function view(message) {
    document.getElementById('name').innerText = JSON.parse(message).usernameTransit + ' '
        + JSON.parse(message).clientsGroup
    document.getElementById('lessons').innerText = JSON.parse(message).nameLessons
    document.getElementById('data_transit').innerText = JSON.parse(message).dataTransit
    document.getElementById('time_transit').innerText = JSON.parse(message).timeTransit
    // document.getElementById('class_group').innerText = JSON.parse(message).clientsGroup
    $('#img').attr('src', JSON.parse(message).personFaceImageUrl);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});