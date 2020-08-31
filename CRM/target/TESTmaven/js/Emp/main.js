$(function () {
    setSession();
    initData();
});

function out() {
    location.href="http://localhost:8080/user/logout"
}
function setSession() {
}

function initData() {
    var name = sessionStorage.getItem("name");
    $("#title_name").html(name)
}