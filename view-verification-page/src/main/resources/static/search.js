$(document).ready(function () {
    $("#search_table").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#table tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    // $("#button_dialog").click().hide();
});

//
// function sleep(milliseconds) {
//     var start = new Date().getTime();
//     for (var i = 0; i < 1e7; i++) {
//         if ((new Date().getTime() - start) > milliseconds){
//             break;
//         }
//     }
// }

// var dotCounter = 0;
// (function addDot() {
//     setTimeout(function() {
//         if (dotCounter++ < 10) {
//             $('#dots').append('.');
//             addDot();
//         }
//     }, 1000);
// })();