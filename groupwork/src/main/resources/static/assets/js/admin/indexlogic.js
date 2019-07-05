//manager functions
function addManager() {
    var userid = document.getElementById("admin-id").value;
    var password = document.getElementById("admin-pass").value;
    var name = document.getElementById("admin-name").value;
    var tel = document.getElementById("admin-contact").value;
    var school = document.getElementById("admin-school").value;
    if ((userid != null && password != null && name != null && tel != null && school != null) && (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/addManager', {
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

function editManager() {
    var userid = document.getElementById("e-admin-id").value;
    var password = document.getElementById("e-admin-pass").value;
    var name = document.getElementById("e-admin-name").value;
    var tel = document.getElementById("e-admin-contact").value;
    var school = $("#e-admin-school").val();
    if ((userid != null && password != null && name != null && tel != null) && (userid.toString().length > 2 && password.length > 2)) {
        axios.post('/editManager', {
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
    inputObj.onchange = function (evt) {
        var selectedFile = evt.target.files[0];
        var reader = new FileReader();
        reader.onload = function (event) {
            var data = event.target.result;
            var workbook = XLSX.read(data, {
                type: 'binary'
            });
            workbook.SheetNames.forEach(function (sheetName) {
                var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
                if (XL_row_object.length > 0) {
                    console.log(XL_row_object);
                    for(var i=0;i<XL_row_object.length;i++){
                        var temp=XL_row_object[i];
                        axios.post('/addTeacher', {
                            userid: temp.id,
                            password: "123456",
                            name: temp.name,
                            tel: temp.tel,
                            academic: temp.academic
                        }).then(response => {
                            console.log("finish "+i.toString());
                        })
                    }
                }
                window.location.reload();
            })
        };
        reader.onerror = function (event) {
            console.error("File could not be read! Code " + event.target.error.code);
        };
        // 读取上传文件为二进制
        reader.readAsBinaryString(selectedFile);
        //console.log(document.getElementById("jsonObject").value);
    }
    inputObj.click();
    console.log(inputObj.value);

});

$("#upload-student").click(function () {
    var inputObj = document.createElement('input')
    inputObj.setAttribute('id', '_ef');
    inputObj.setAttribute('type', 'file');
    inputObj.setAttribute("style", 'visibility:hidden');
    document.body.appendChild(inputObj);
    inputObj.onchange = function (evt) {
        var selectedFile = evt.target.files[0];
        var reader = new FileReader();
        reader.onload = function (event) {
            var data = event.target.result;
            var workbook = XLSX.read(data, {
                type: 'binary'
            });
            workbook.SheetNames.forEach(function (sheetName) {
                var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
                if (XL_row_object.length > 0) {
                    console.log(JSON.stringify(XL_row_object));
                    for(var i=0;i<XL_row_object.length;i++){
                        var temp=XL_row_object[i];
                        axios.post('/addStudent', {
                            userid: temp.id,
                            password: "123456",
                            name: temp.name,
                            tel: temp.tel,
                            academic: temp.academic
                        }).then(response => {
                            console.log("finish"+i.toString());
                        })
                    }

                    window.location.reload();
                    
                }

            })
        };
        reader.onerror = function (event) {
            console.error("File could not be read! Code " + event.target.error.code);
        };
        // 读取上传文件为二进制
        reader.readAsBinaryString(selectedFile);
        //console.log(document.getElementById("jsonObject").value);
    }
    inputObj.click();
    console.log(inputObj.value);

});

//topic review 

function passTopic() {
    var id = $("#topic_review_id").val();
    axios.post('acceptTopic', {
        topicid: id
    })
        .then(res => {
            window.location.reload();
        })
        .catch(err => {
            console.error(err);
        })
}

function refuseTopic() {
    var id = $("#topic_review_id").val();
    axios.post('refuseTopic', {
        topicid: id
    })
        .then(res => {
            window.location.reload();
        })
        .catch(err => {
            console.error(err);
        })
}

//status manager

$(".state-btn").click(function () {

    var code = $(this).attr("statecode");
    axios.post("/setPeriod", {
        segid: code
    })
        .then(res => {
            console.log(res);
            window.location.reload();
        })
        .catch(err => {
            console.error(err);
        })
});


//subgroup

$("#subgroup-btn").click(function(){
    if(groupedFinalLine==null){
        ArrangeStudentTeach(teachIdArray,stuIdArray);
    }
    for(var i=0;i<groupedFinalLine.length;i++){
        var temp=groupedFinalLine[i];
        axios.post('groupsetAcademic',{
            type:temp.type,
            userid:temp.id,
            groupid:temp.gid
        })
        .then(res => {
            console.log("success "+i.toString());
        })
        .catch(err => {
            console.error(err); 
        })
    }
});

$("#arrange-btn").click(function () {
    ArrangeStudentTeach(teachIdArray,stuIdArray);
})

//msg functions
function deleteMsg(){
    var mid=$("#del-msg").attr("mid");
    console.log(mid);
    axios.post('deleteMessage',{
        messageid:mid
    })
    .then(res => {
        console.log(res);
        window.location.reload();
    })
    .catch(err => {
        console.error(err); 
    })
}

function addMsg(){
    var title =$("#add-msg-name").val();
    var content=$("#add-msg-content").val();

    axios.post("/addMessage",{
        title:title,
        content:content
    })
    .then(res => {
        window.location.reload();
    })
    .catch(err => {
        console.error(err); 
    })
}

function SubPass(){
    var stuid=document.getElementById("sub-content").getAttribute("sid");
    axios.post('agreedManager',{
        studentid:stuid
    })
    .then(res => {
        console.log(res)
    })
    .catch(err => {
        console.error(err); 
    })
}
