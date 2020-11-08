var charstoformid = '_ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz'.split('');
idlength = 10;
var tempId = function () {
    var uniqid = '';
    for (var i = 0; i < idlength; i++) {
        uniqid += charstoformid[Math.floor(Math.random() * charstoformid.length)];
    }
    return uniqid;
};
targer = tempId();
new_id = tempId();

// alert(bb)
$('#bnt_details').attr('data-target', '#' + targer);
$('#bnt_details').attr('id', new_id);
$('#model-details').attr('id', targer);
