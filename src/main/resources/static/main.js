document.addEventListener("DOMContentLoaded", init);
let content = null;
let token = "";

function init() {
    content = document.getElementById("content");
    loadItemsList();
}

function post() {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/show";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader('Csrf-Token', false);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status == 200 || xhr.status == 202){
                func(xhr.responseText);
            }
            else
                funcerr(xhr.responseText)
        }
    };
    var data = JSON.stringify(object);
    xhr.send(data);
}

function loadItemsList(items) {
    content.innerHTML = "<h1>Список</h1>";
    items.forEach(item => {
        let btn = document.createElement("button");
        btn.innerHTML = item.name + " : " + item.count;
        if (item.bought)
            btn.className="item-bought";
        else
            btn.className="item";

        content.appendChild(btn);
    });
}