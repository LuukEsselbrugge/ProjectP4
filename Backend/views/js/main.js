var boardSubmissions = null;
var currentSubmission = 0;
var settings = null;

function updateBoard(){
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/board/submissions/?token="+_GET()["token"], true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            boardSubmissions = xhr.response;
        }
    };
    xhr.send();

    var xhr2 = new XMLHttpRequest();
    xhr2.open('GET', "/board/users/?token="+_GET()["token"], true);
    xhr2.responseType = 'json';
    xhr2.onload = function () {
        var status = xhr2.status;
        if (status === 200) {
            boardUsers = xhr2.response;
            updateUsers(xhr2.response);
        }
    };
    xhr2.send();

    var xhr3 = new XMLHttpRequest();
    xhr3.open('GET', "/board/settings/?token="+_GET()["token"], true);
    xhr3.responseType = 'json';
    xhr3.onload = function () {
        var status = xhr3.status;
        if (status === 200) {
            settings = xhr3.response;
            if(settings.DashboardEnabled == 0){
                document.documentElement.style.backgroundColor = "black";
                document.getElementsByTagName("body")[0].style.display = "none";
            }else{
                document.documentElement.style.backgroundColor = "";
                document.getElementsByTagName("body")[0].style.display = "";
            }
        }
    };
    xhr3.send();
    setTimeout(updateBoard, 5000);
}

function updateUsers(users){
    document.getElementsByClassName("Users")[0].innerHTML = "";
    for(var key in users) {
        template = document.getElementsByClassName("Usertemplate")[0].cloneNode(true);
        template.style.display = "";
        template.getElementsByClassName("userName")[0].innerHTML = users[key].Firstname + " " + users[key].Lastname;
        if(users[key].Image == "") {
            template.getElementsByClassName("UserImage")[0].innerHTML = users[key].Firstname.charAt(0).toUpperCase() + users[key].Lastname.charAt(0).toUpperCase();
        }else{
            template.getElementsByClassName("UserImage")[0].style.backgroundImage = "url('/images/"+users[key].Image+"')";
        }
        if(users[key].AMode == "NA"){
            template.getElementsByClassName("boardAV")[0].classList = "boardAV boardNA";
        }
        if(users[key].AMode == "AU"){
            if(users[key].Available == 1) {
                template.getElementsByClassName("boardAV")[0].classList = "boardAV";
            }else{
                template.getElementsByClassName("boardAV")[0].classList = "boardAV boardNA";
            }
        }
        document.getElementsByClassName("Users")[0].appendChild(template);
    }
}

function nextSubmission(){
    if(boardSubmissions != null) {
        if(boardSubmissions.length == 0){
            document.getElementsByClassName("previewTitle")[0].innerHTML = "";
            document.getElementsByClassName("contentBlockDescription")[0].innerHTML = "Geen items";
            currentSubmission = 0;

        }else{
            if(boardSubmissions[currentSubmission] != null) {
                document.getElementsByClassName("previewTitle")[0].innerHTML = ""; // boardSubmissions[currentSubmission].Title;
                document.getElementsByClassName("contentBlockDescription")[0].innerHTML = boardSubmissions[currentSubmission].Body;
            }
        }
        currentSubmission++;
        if(currentSubmission == boardSubmissions.length || boardSubmissions.length == 1){
            currentSubmission = 0;
        }
    }else{
        currentSubmission = 0;
    }
    if(settings != null) {
        setTimeout(nextSubmission, settings.DashboardSeconds * 1000);
    }else{
        setTimeout(nextSubmission, 1000);
    }
}

function _GET() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function updateAV(elem,value){
    buttons = document.getElementsByClassName("AVButton");
    Array.from(buttons).forEach(function(element) {
        element.disabled = true;
    });

    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/availability/update/?AVMode=" + value, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            json = xhr.response;
            Array.from(buttons).forEach(function(element) {
                element.getElementsByTagName("i")[0].className = "mdi mdi-circle";
                element.disabled = false;
            });
            elem.disabled = true;
            elem.getElementsByTagName("i")[0].className = "mdi mdi-check-circle";
        }
    };
    xhr.send();
}

function updateDAV(elem,dayofweek){
    inputs = elem.parentElement.getElementsByClassName("inputNumber");
    starttime = inputs[0].value+":"+inputs[1].value;
    endtime = inputs[2].value+":"+inputs[3].value;

    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/availability/update/?DayOfWeek=" + dayofweek +"&StartTime="+starttime+"&EndTime="+endtime, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {

        }
    };
    xhr.send();
}

function updateDay(elem,dayofweek){
    if(elem.dataset.enabled == 1){
        elem.dataset.enabled = 0;
    }else{
        elem.dataset.enabled = 1;
    }

    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/availability/update/?DayOfWeek=" + dayofweek +"&Enabled="+ elem.dataset.enabled, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            if(elem.dataset.enabled == 1){
                elem.classList = "mdi mdi-check-circle contentButtonRight";
            }else{
                elem.classList = "mdi mdi-circle-outline contentButtonRight";
            }
        }
    };
    xhr.send();
}

function submissionChanged(elem){
    buttonsave = document.getElementsByClassName("buttonSave")[0];
    buttonsave.innerHTML = '<i class="mdi mdi-floppy"></i> Opslaan';
    buttonsave.disabled = false;

    document.getElementsByClassName("previewTitle")[0].innerHTML =  document.getElementsByClassName("contentInput")[0].value;
    document.getElementsByClassName("previewDesc")[0].innerHTML =  document.getElementsByClassName("contentInput")[1].value;
}

function userChanged(elem){
    buttonsave = document.getElementsByClassName("buttonSave")[0];
    buttonsave.innerHTML = '<i class="mdi mdi-floppy"></i> Opslaan';
    buttonsave.disabled = false;
}


function systemChanged(elem){
    buttonsave = document.getElementsByClassName("buttonSave")[0];
    buttonsave.innerHTML = '<i class="mdi mdi-floppy"></i> Opslaan';
    buttonsave.disabled = false;
}


function submissionLoaded(){
    document.getElementsByClassName("previewTitle")[0].innerHTML =  document.getElementsByClassName("contentInput")[0].value;
    document.getElementsByClassName("previewDesc")[0].innerHTML =  document.getElementsByClassName("contentInput")[1].value;
}

function submissionAdd(elem){
    elem.disabled = true;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/submissions/add", true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            window.location = "/edit/"+xhr.response;
        }
    };
    xhr.send();
}

function submissionSave(elem){
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/edit/update/?SubmissionID=" + elem.dataset.submissionid +"&Title="+ document.getElementsByClassName("contentInput")[0].value
        +"&Body="+ document.getElementsByClassName("contentInput")[1].value.replace(/\n/g, '%0A'), true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            if(JSON.parse(xhr.response)){
                elem.disabled = true;
                elem.innerHTML = '<i class="mdi mdi-check"></i> Opgeslagen';
            }
        }
    };
    xhr.send();
}

function updateVisible(elem){
    if(elem.dataset.visible == "1"){
        elem.dataset.visible = "0";
    }else{
        elem.dataset.visible = "1";
    }
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/submissions/visible/?id=" + elem.dataset.id +"&visible="+elem.dataset.visible, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            if(JSON.parse(xhr.response)){
                if(elem.dataset.visible == "1"){
                    elem.innerHTML = '<i class="mdi mdi-eye"></i>';
                }else{
                    elem.innerHTML = '<i class="mdi mdi-eye-off"></i>';
                }

            }
        }
    };
    xhr.send();
}

function systemSave(elem){
    seconds = document.getElementsByClassName("contentInput")[0].value;
    enabled = document.getElementsByClassName("togglePermission")[0].dataset.enabled;

    if(!document.getElementById("form").reportValidity()){
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/system/update/?Seconds=" + seconds +"&Enabled="+ enabled, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            if(JSON.parse(xhr.response)){
                elem.disabled = true;
                elem.innerHTML = '<i class="mdi mdi-check"></i> Opgeslagen';
                passwordRepeat.classList = "contentInput";
                passwordRepeat.placeholder = "";

                fullname.innerHTML = firstname + " " + lastname;
                namecode.innerHTML = firstname.charAt(0).toUpperCase() + lastname.charAt(0).toUpperCase();
            }
        }
    };
    xhr.send();
}

function userSave(elem){
    firstname = document.getElementsByClassName("contentInput")[0].value;
    lastname  = document.getElementsByClassName("contentInput")[1].value;
    email = document.getElementsByClassName("contentInput")[2].value;
    password = document.getElementsByClassName("contentInput")[3].value;
    passwordRepeat = document.getElementsByClassName("contentInput")[4];
    admin = document.getElementsByClassName("togglePermission")[0].dataset.enabled;
    visible = document.getElementsByClassName("togglePermission")[1].dataset.enabled;
    publish = document.getElementsByClassName("togglePermission")[2].dataset.enabled;

    fullname = document.getElementById("fullname");
    namecode = document.getElementById("namecode");
    amode = "";

    buttons = document.getElementsByClassName("AVButton");
    Array.from(buttons).forEach(function(element) {
       if(element.disabled){
           amode = element.dataset.mode;
       }
    });

    if(!document.getElementById("form").reportValidity()){
        return;
    }

    if(password != passwordRepeat.value){
        password = "";
        passwordRepeat.classList = "contentInput contentInputRed";
        passwordRepeat.value = "";
        passwordRepeat.placeholder = "Wachtwoorden komen niet overeen";
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/users/update/?UserID=" + elem.dataset.userid +"&Firstname="+ firstname
        +"&Lastname="+ lastname + "&Email=" + email + "&Admin=" + admin + "&Visible=" + visible + "&Password=" + password + "&AMode="+amode + "&Publish="+publish, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            if(JSON.parse(xhr.response)){
                elem.disabled = true;
                elem.innerHTML = '<i class="mdi mdi-check"></i> Opgeslagen';
                passwordRepeat.classList = "contentInput";
                passwordRepeat.placeholder = "";

                fullname.innerHTML = firstname + " " + lastname;
                namecode.innerHTML = firstname.charAt(0).toUpperCase() + lastname.charAt(0).toUpperCase();
            }
        }
    };
    xhr.send();
}

function userAdd(elem){
    elem.disabled = true;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/users/add", true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            window.location = "/users/"+xhr.response;
        }
    };
    xhr.send();
}


function leadingZero(elem) {
    var minuteValue = elem.value;
    if (minuteValue.toString().length < 2) {
        minuteValue = "0" + minuteValue;
    }
    elem.value = minuteValue;
}



function changeTab(elem,tab){
    var tabs = document.getElementsByClassName("tab");
    Array.from(tabs).forEach(function(element) {
        element.style.display = "none";
    });

    var menuitems = document.getElementsByClassName("topMenuItem");
    Array.from(menuitems).forEach(function(element) {
        element.classList.remove("topMenuItemSelected");
    });

    document.getElementsByClassName(tab)[0].style.display = "";
    elem.classList.add("topMenuItemSelected");
}

function insertAtCursor(start,end) {
    myField = document.getElementsByClassName('contentInput')[1];
    //IE support
    if (document.selection) {
        myField.focus();
        sel = document.selection.createRange();
        sel.text = myValue;
    }
    //MOZILLA and others
    else if (myField.selectionStart || myField.selectionStart == '0') {
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;
        var original = myField.value.substring(startPos, endPos);
        myField.value = myField.value.substring(0, startPos) + start + original + end + myField.value.substring(endPos, myField.value.length);
    } else {
        myField.value += myValue;
    }
    submissionChanged(this);
}

function insertTable(){
    myField = document.getElementsByClassName('contentInput')[1];
    //IE support
    if (document.selection) {
        myField.focus();
        sel = document.selection.createRange();
        sel.text = myValue;
    }
    //MOZILLA and others
    else if (myField.selectionStart || myField.selectionStart == '0') {
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;
        var table = "<table>\n" +
            "  <tr>\n" +
            "    <th></th>\n" +
            "    <th></th> \n" +
            "    <th></th>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td></td>\n" +
            "    <td></td> \n" +
            "    <td></td>\n" +
            "  </tr>\n" +
            "</table>";

        myField.value = myField.value.substring(0, startPos) + table + myField.value.substring(endPos, myField.value.length);
    } else {
        myField.value += myValue;
    }
}

function insertImage(e){
    myField = document.getElementsByClassName('contentInput')[1];

    if (myField.selectionStart || myField.selectionStart == '0') {
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;

        myField.value = myField.value.substring(0, startPos) + '<img width="" height="" src="'+e.dataset.url+'"/>' + myField.value.substring(endPos, myField.value.length);
        if ("createEvent" in document) {
            var evt = document.createEvent("HTMLEvents");
            evt.initEvent("keyup", false, true);
            myField.dispatchEvent(evt);
        }
        else {
            myField.fireEvent("onchange");
        }
    } else {
        myField.value += '<img src="'+e.dataset.url+'">';
    }
}

function uploadFile(e){
    formData = new FormData();
    formData.append("photo", e.files[0]);
    e.value = "";

    var xhr = new XMLHttpRequest();
    xhr.open('POST', "/submissions/upload?SubmissionID="+e.dataset.id, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        json = xhr.response;
        if (status == 200 && json[0] != null) {
            editimage = document.getElementsByClassName("editImageTemplate")[0].cloneNode(true);
            button = editimage.getElementsByClassName("buttonRight")[0];
            button.innerHTML = '<i class="mdi mdi-plus"></i> '+json[0]["Filename"];
            button.dataset.url = "/images/"+json[0]["SubmissionID"]+"-"+json[0]["Filename"];

            buttonSmall = editimage.getElementsByClassName("buttonRightSmall")[0];
            buttonSmall.dataset.submissionid = json[0]["SubmissionID"];
            buttonSmall.dataset.filename = json[0]["Filename"];
            editimage.style.display = "";
            editimage.classList = "editImages";
            document.getElementsByClassName("editImages")[0].appendChild(editimage);
        }else{
            nicealert("Bestand niet geüpload");
        }
    };
    xhr.send(formData);
}

function deleteFile(e){
    console.log(e);
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/submissions/deleteAttachment/?id=" + e.dataset.submissionid +"&filename="+ e.dataset.filename, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            if(JSON.parse(xhr.response)){
               e.parentNode.parentNode.removeChild(e.parentNode);
            }
        }
    };
    xhr.send();
}

function toggleCheck(e){
    if(e.dataset.enabled == "1"){
        e.dataset.enabled = "0";
        e.classList = "togglePermission mdi mdi-circle-outline";
    }else{
        e.dataset.enabled = "1";
        e.classList = "togglePermission mdi mdi-check-circle";
    }
    userChanged(e);
}

function buttonCheck(e){
    buttons = document.getElementsByClassName("AVButton");
    Array.from(buttons).forEach(function(element) {
        element.getElementsByTagName("i")[0].className = "mdi mdi-circle";
        element.disabled = false;
    });
    e.disabled = true;
    e.getElementsByTagName("i")[0].className = "mdi mdi-check-circle";

    userChanged(e);
}

function uploadProfileImage(e){
    formData = new FormData();
    formData.append("photo", e.files[0]);
    e.value = "";

    var xhr = new XMLHttpRequest();
    xhr.open('POST', "/users/updateImage?UserID="+e.dataset.id, true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        json = xhr.response;
        if (status == 200 && json[0]) {
            img = document.getElementsByClassName("UserImageLarge")[0];
            img.style.backgroundImage = "url('"+json[1]+"')";
            img.style.fontSize = "0px";
            document.getElementsByClassName("UserImageHover")[0].style.display = "block";

        }else{
            nicealert("Foto niet geüpload probeer het opnieuw");
        }
    };
    xhr.send(formData);
}

function confirmbox(message,url,event) {
    event.stopPropagation();
    alert = document.getElementsByClassName("alert")[0];
    alert.getElementsByClassName("title")[0].innerHTML = message;
    alert.style.display = "block";

    alert.getElementsByClassName("alertYes")[0].addEventListener("click", function(){
       window.location = url;
    });

    alert.getElementsByClassName("alertNo")[0].addEventListener("click", function(){
        alert.style.display = "none";
    });

    alert.scrollIntoView({block: "center"});
}

function nicealert(message) {
    alert = document.getElementsByClassName("alert")[0];
    alert.getElementsByClassName("title")[0].innerHTML = message;
    alert.style.display = "block";
    alert.getElementsByClassName("alertYes")[0].style.display = "none";
    alert.getElementsByClassName("alertNo")[0].style.display = "none";
    alert.getElementsByClassName("alertOk")[0].style.display = "block";

    alert.getElementsByClassName("alertOk")[0].addEventListener("click", function(){
        alert.style.display = "none";
    });

    alert.scrollIntoView({block: "center"});
}

