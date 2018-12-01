let button = document.getElementById('button');
function showDialog(message) {
    alert(message);
    return 'showDialog(message) called';
}
function showToast(text) {
    let returnValue = JsInterface.showToast(text);
    button.innerHTML = returnValue;
}