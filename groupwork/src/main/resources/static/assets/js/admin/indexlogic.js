//manager functions
function addManager() {
    var userid = document.getElementById("admin-id").value;
    var password = document.getElementById("admin-pass").value;
    var name = document.getElementById("admin-name").value;
    var tel = document.getElementById("admin-contact").value;
    if ((userid != null && password != null && name != null && tel != null) && (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/addManager', {
            userid: userid,
            password: password,
            name: name,
            tel: tel
        }).then(response => {
            window.reload();
        })
    }
}

function editManager() {
    var userid = document.getElementById("e-admin-id").value;
    var password = document.getElementById("e-admin-pass").value;
    var name = document.getElementById("e-admin-name").value;
    var tel = document.getElementById("e-admin-contact").value;
    if ((userid != null && password != null && name != null && tel != null) && (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/editManager', {
            userid: userid,
            password: password,
            name: name,
            tel: tel
        }).then(response => {
            window.reload();
        })
    }

}

function deleteManager() {
    var userid = document.getElementById("del_admin_id").value;
    console.log(userid.toString());
    axios.post('/deleteManager', {
        userid: userid
    }).then(response => {
        axios.post('/showManager').then(response => {
            //console.log(response.data);
            RefreshAdiminlist(response.data);
        });
    })
}


//teacher
function addTeacher() {
    var userid = document.getElementById("teach-id").value;
    var userAcd = document.getElementById("teach-school").value;
    var password = document.getElementById("teach-pass").value;
    var name = document.getElementById("teach-name").value;
    var tel = document.getElementById("teach-contact").value;
    if ((userid != null && password != null && name != null && tel != null) &&
        (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/addTeacher', {
            userid: userid,
            password: password,
            name: name,
            tel: tel,
            academic: userAcd
        }).then(response => {
            window.reload();
        })
    }
}

function editTeacher() {
    var userid = document.getElementById("e-teach-id").value;
    var password = document.getElementById("e-teach-pass").value;
    var name = document.getElementById("e-teach-name").value;
    var tel = document.getElementById("e-teach-contact").value;
    var school = document.getElementById("e-teach-school").value;
    if ((userid != null && password != null && name != null && tel != null) && (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/editTeacher', {
            userid: userid,
            password: password,
            name: name,
            tel: tel,
            academic: school
        }).then(response => {
            window.reload();
        })
    }

}

function deleteTeacher() {
    var userid = document.getElementById("del_teach_id").value;
    console.log(userid.toString());
    axios.post('/deleteTeacher', {
        userid: userid
    }).then(response => {
        axios.post('/showTeacher').then(response => {
            //console.log(response.data);
            RefreshTeachlist(response.data);
        });
    })
}

//student
function addStudent() {
    var userid = document.getElementById("stud-id").value;
    var userAcd = document.getElementById("stud-school").value;
    var password = document.getElementById("stud-pass").value;
    var name = document.getElementById("stud-name").value;
    var tel = document.getElementById("stud-contact").value;
    if ((userid != null && password != null && name != null && tel != null) && (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/addStudent', {
            userid: userid,
            password: password,
            name: name,
            tel: tel,
            academic: userAcd
        }).then(response => {
            window.reload();
        })
    }
}

function editStudent() {
    var userid = document.getElementById("e-stud-id").value;
    var password = document.getElementById("e-stud-pass").value;
    var name = document.getElementById("e-stud-name").value;
    var tel = document.getElementById("e-stud-contact").value;
    var school = document.getElementById("e-stud-school").value;
    if ((userid != null && password != null && name != null && tel != null) && (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/editStudent', {
            userid: userid,
            password: password,
            name: name,
            tel: tel,
            academic: school
        }).then(response => {
            window.reload();
        })
    }

}

function deleteStudent() {
    var userid = document.getElementById("del_stud_id").value;
    console.log(userid.toString());
    axios.post('/deleteStudent', {
        userid: userid
    }).then(response => {
        axios.post('/showStudent').then(response => {
            //console.log(response.data);
            RefreshStudlist(response.data);
        });
    })
}

//Upload File

$("#upload-teach").click(function () {
    var inputObj = document.createElement('input')
    inputObj.setAttribute('id', '_ef');
    inputObj.setAttribute('type', 'file');
    inputObj.setAttribute("style", 'visibility:hidden');
    document.body.appendChild(inputObj);
    inputObj.click();
    console.log(inputObj.value);
    
});