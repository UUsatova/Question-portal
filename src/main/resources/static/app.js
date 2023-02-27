var stompClient = null;
var a = 1;



function connect(options) {
        var socket = new SockJS('/answers');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe("/topic/answers", function (answer) {
                showAnswer(answer);
            });
        });

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}


function showAnswer(answer){
            $("#myTable").prepend("<tr><td>my data</td><td>more data</td></tr>");
    console.log("subscrube" + answer);
}



$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( ".edit" ).click(function() { connect(); });
    //$( "#disconnect" ).click(function() { disconnect(); });
   // $( "#send" ).click(function() { sendAnswer(); });
});