/**
 *  这个是管理员页面控制渲染的脚本
 * 
 */

var teachIdArray = {};
var stuIdArray = {};
var groupedFinalLine = {};

var Admin_List_Render = document.getElementById("Admin_List_Render");
var Teacher_List_Render = document.getElementById("Teacher_List_Render");
var Student_List_Render = document.getElementById("Student_List_Render");
var Topic_List_Render = document.getElementById("Topic_List_Render");
var Msg_List_Render = document.getElementById("Msg_List_Render");

function initPages() {
    //check the cookies
    axios.post('/checkCookie').then(response => {
        if (response.data === 0) {
            window.location.href = 'login.html';
        }
    });
    //init the manager page
    axios.post('/showManager').then(response => {
        //console.log(response.data);
        RefreshAdiminlist(response.data);
    });
    //init the teacher pages
    axios.post('/showTeacher').then(response => {
        RefreshTeachlist(response.data);
    });
    //init the teacher pages
    axios.post('/showStudent').then(response => {
        RefreshStudlist(response.data);
    });

    //init the topic review pages
    axios.post('/showAcademicTopicManager')
        .then(res => {
            RefreshTopic(res.data);
        })
        .catch(err => {
            console.error(err);
        })
    //
    axios.post('/showPeriod')
        .then(res => {
            checkStateRender(res.data.status)
        })
        .catch(err => {
            console.error(err);
        })

    //var stuarr,teacharr;
    axios.post('/groupshowAcademicStudent')
        .then(res => {
            teachIdArray = res.data[1];
            stuIdArray = res.data[0];
            RefreshUngroup(res.data[1], res.data[0]);
        })
        .catch(err => {
            console.error(err);
        });

    axios.post("/showMessage")
        .then(res => {
            var data = res.data;
            data.reverse();
            RefreshMsg(data)
        })
        .catch(err => {
            console.error(err);
        })

    axios.post('/showAppliedStudentsManager')
        .then(res => {
            RefreshSub(res.data);
        })
        .catch(err => {
            console.error(err);
        })

}

window.onload = function () {
    initPages();
}

function RefreshAdiminlist(adminarray) {
    ClearRenderer(Admin_List_Render);
    for (var i = 0; i < adminarray.length; i++) {
        AttachChildren(Admin_List_Render, MakeUpAdmin(adminarray[i].name, adminarray[i].password, adminarray[i].userid, adminarray[i].tel, adminarray[i].academic));
    }
}

function RefreshTeachlist(teacharray) {
    ClearRenderer(Teacher_List_Render);
    for (var i = 0; i < teacharray.length; i++) {
        AttachChildren(Teacher_List_Render, MakeUpTeach(teacharray[i].name, teacharray[i].password, teacharray[i].userid, teacharray[i].tel, teacharray[i].academic));
    }
}

function RefreshStudlist(studarray) {
    ClearRenderer(Student_List_Render);
    for (var i = 0; i < studarray.length; i++) {
        AttachChildren(Student_List_Render, MakeUpStud(studarray[i].name, studarray[i].password, studarray[i].userid, studarray[i].tel, studarray[i].academic));
    }
}

function RefreshTopic(topicarray) {
    ClearRenderer(Topic_List_Render);
    for (var i = 0; i < topicarray.length; i++) {
        var temp = topicarray[i];
        if (temp.state == 0) {

            AttachChildren(Topic_List_Render, MakeUpTopic(temp.topic, temp.topicid, temp.name, temp.description));
        }

    }
}

/**

<tr class="gradeX">
    <td>管理员A</td>
    <td>A-00001</td>
    <td>1234567890</td>
    <td>
        <div class="tpl-table-black-operation">
            <a href="javascript:;">
                <i class="am-icon-pencil"></i> 编辑
            </a>
            <a href="javascript:;" class="tpl-table-black-operation-del">
                <i class="am-icon-trash"></i> 删除
            </a>
        </div>
    </td>
</tr>

 */

function CleanAdminEdit() {
    document.getElementById("e-admin-id").value = "";
    document.getElementById("e-admin-pass").value = "";
    document.getElementById("e-admin-name").value = "";
    document.getElementById("e-admin-contact").value = "";
}

function SetEditAdmin(name, pass, id, tel, school) {
    document.getElementById("e-admin-id").value = id;
    document.getElementById("e-admin-pass").value = pass;
    document.getElementById("e-admin-name").value = name;
    document.getElementById("e-admin-contact").value = tel;
    document.getElementById("e-admin-school").value = school;
}

function SetTopicContent(name, content) {
    $("#s-topic-name").val(name);
    $("#s-topic-brief").val(content);
}

function SetEditTeach(name, pass, id, tel, school) {
    document.getElementById("e-teach-id").value = id;
    document.getElementById("e-teach-pass").value = pass;
    document.getElementById("e-teach-name").value = name;
    document.getElementById("e-teach-contact").value = tel;
    document.getElementById("e-teach-school").value = school;
}

function SetEditStud(name, pass, id, tel, school) {
    document.getElementById("e-stud-id").value = id;
    document.getElementById("e-stud-pass").value = pass;
    document.getElementById("e-stud-name").value = name;
    document.getElementById("e-stud-contact").value = tel;
    document.getElementById("e-stud-school").value = school;
}

function MakeUpAdmin(_name, _pass, _id, _contact, _school) {
    var temp = MakeUpElement("tr", "", "gradeX");

    var name = MakeUpElement('td', _name, "");
    var id = MakeUpElement('td', _id, "");
    var academic = MakeUpElement('td', _school, "");
    var contact = MakeUpElement('td', _contact, "");
    var btnRoot = MakeUpElement('td', "", "");
    var btnFather = MakeUpElement('div', "", "tpl-table-black-operation");
    var editBtn = MakeUpElement("a", "编辑  ", "");
    editBtn.setAttribute('href', "javascript:;");
    editBtn.setAttribute("content_data", JSON.stringify({ name: _name, pass: _pass, id: _id, tel: _contact, school: _school }));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal', "{target: '#edit-admin',closeViaDimmer: 0, width: 600, height: 500}");
    editBtn.addEventListener('click', function () {
        //console.log("edit");
        //CleanAdminEdit();
        var _data = JSON.parse(this.getAttribute("content_data"));
        //console.log(_data);
        SetEditAdmin(_data.name, _data.pass, _data.id, _data.tel, _data.school);
    });
    var deleteBtn = MakeUpElement('a', "删除  ", "tpl-table-black-operation-del");
    deleteBtn.setAttribute('href', "javascript:;");
    deleteBtn.setAttribute('data-am-modal', "{target: '#del-admin', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("data-contentr", _id);
    deleteBtn.addEventListener('click', function () {
        document.getElementById("del_admin_id").placeholder = deleteBtn.getAttribute("data-contentr");
        document.getElementById("del_admin_id").value = deleteBtn.getAttribute("data-contentr");
    });
    var editIcon = MakeUpElement('i', '', "am-icon-pencil");
    var deleteIcon = MakeUpElement('i', '', "am-icon-trash");
    editBtn.appendChild(editIcon);
    deleteBtn.appendChild(deleteIcon);
    //editBtn.appendChild(document.createTextNode(""));
    btnFather.appendChild(editBtn);
    //deleteBtn.appendChild(document.createTextNode(" 删除\n     "));
    btnFather.appendChild(deleteBtn);
    btnRoot.appendChild(btnFather);
    temp.appendChild(name);
    temp.appendChild(id);
    temp.appendChild(academic)
    temp.appendChild(contact);
    temp.appendChild(btnRoot);
    return temp;
}

function MakeUpTeach(_name, _pass, _id, _contact, _school) {
    var temp = MakeUpElement("tr", "", "gradeX");

    var name = MakeUpElement('td', _name, "");
    var id = MakeUpElement('td', _id, "");
    var school = MakeUpElement('td', _school, "");
    var contact = MakeUpElement('td', _contact, "");
    var btnRoot = MakeUpElement('td', "", "");
    var btnFather = MakeUpElement('div', "", "tpl-table-black-operation");
    var editBtn = MakeUpElement("a", "编辑  ", "");
    editBtn.setAttribute('href', "javascript:;");
    editBtn.setAttribute("content_data", JSON.stringify({ name: _name, pass: _pass, id: _id, tel: _contact, school: _school }));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal', "{target: '#edit-teach',closeViaDimmer: 0, width: 600, height: 600}");
    editBtn.addEventListener('click', function () {
        console.log("edit");
        //CleanAdminEdit();
        var _data = JSON.parse(editBtn.getAttribute("content_data"));
        console.log(_data);
        SetEditTeach(_data.name, _data.pass, _data.id, _data.tel, _data.school);
    });
    var deleteBtn = MakeUpElement('a', "删除  ", "tpl-table-black-operation-del");
    deleteBtn.setAttribute('href', "javascript:;");
    deleteBtn.setAttribute('data-am-modal', "{target: '#del-teach', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("data-contentr", _id);
    deleteBtn.addEventListener('click', function () {
        document.getElementById("del_teach_id").placeholder = deleteBtn.getAttribute("data-contentr");
        document.getElementById("del_teach_id").value = deleteBtn.getAttribute("data-contentr");
    });
    var editIcon = MakeUpElement('i', '', "am-icon-pencil");
    var deleteIcon = MakeUpElement('i', '', "am-icon-trash");
    editBtn.appendChild(editIcon);
    deleteBtn.appendChild(deleteIcon);
    //editBtn.appendChild(document.createTextNode(""));
    btnFather.appendChild(editBtn);
    //deleteBtn.appendChild(document.createTextNode(" 删除\n     "));
    btnFather.appendChild(deleteBtn);
    btnRoot.appendChild(btnFather);
    temp.appendChild(name);
    temp.appendChild(id);
    temp.appendChild(school);
    temp.appendChild(contact);
    temp.appendChild(btnRoot);
    return temp;
}

function MakeUpStud(_name, _pass, _id, _contact, _school) {
    var temp = MakeUpElement("tr", "", "gradeX");

    var name = MakeUpElement('td', _name, "");
    var id = MakeUpElement('td', _id, "");
    var school = MakeUpElement('td', _school, "");
    var contact = MakeUpElement('td', _contact, "");
    var btnRoot = MakeUpElement('td', "", "");
    var btnFather = MakeUpElement('div', "", "tpl-table-black-operation");
    var editBtn = MakeUpElement("a", "编辑  ", "");
    editBtn.setAttribute('href', "javascript:;");
    editBtn.setAttribute("content_data", JSON.stringify({ name: _name, pass: _pass, id: _id, tel: _contact, school: _school }));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal', "{target: '#edit-stud',closeViaDimmer: 0, width: 600, height: 600}");
    editBtn.addEventListener('click', function () {
        console.log("edit stud");

        var _data = JSON.parse(this.getAttribute("content_data"));
        console.log(_data);
        SetEditStud(_data.name, _data.pass, _data.id, _data.tel, _data.school);
    });
    var deleteBtn = MakeUpElement('a', "删除  ", "tpl-table-black-operation-del");
    deleteBtn.setAttribute('href', "javascript:;");
    deleteBtn.setAttribute('data-am-modal', "{target: '#del-stud', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("data-contentr", _id);
    deleteBtn.addEventListener('click', function () {
        document.getElementById("del_stud_id").placeholder = deleteBtn.getAttribute("data-contentr");
        document.getElementById("del_stud_id").value = deleteBtn.getAttribute("data-contentr");
    });
    var editIcon = MakeUpElement('i', '', "am-icon-pencil");
    var deleteIcon = MakeUpElement('i', '', "am-icon-trash");
    editBtn.appendChild(editIcon);
    deleteBtn.appendChild(deleteIcon);
    //editBtn.appendChild(document.createTextNode(""));
    btnFather.appendChild(editBtn);
    //deleteBtn.appendChild(document.createTextNode(" 删除\n     "));
    btnFather.appendChild(deleteBtn);
    btnRoot.appendChild(btnFather);
    temp.appendChild(name);
    temp.appendChild(id);
    temp.appendChild(school);
    temp.appendChild(contact);
    temp.appendChild(btnRoot);
    return temp;
}

function MakeUpTopic(_name, _id, _teach, _content) {
    var temp = MakeUpElement("tr", "", "gradeX");

    var name = MakeUpElement('td', _name, "");
    var id = MakeUpElement('td', _id, "");
    //var school = MakeUpElement('td', _school, "");
    var contact = MakeUpElement('td', _teach, "");
    var btnRoot = MakeUpElement('td', "", "");
    var btnFather = MakeUpElement('div', "", "tpl-table-black-operation");
    var ShowBtn = MakeUpElement("a", "详情  ", "");
    ShowBtn.setAttribute('href', "javascript:;");
    ShowBtn.setAttribute("content_data", JSON.stringify({ name: _name, id: _id, teach: _teach, content: _content }));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    ShowBtn.setAttribute('data-am-modal', "{target: '#show-topic',closeViaDimmer: 0, width: 600, height: 600}");
    ShowBtn.addEventListener('click', function () {
        console.log("edit");
        //CleanAdminEdit();
        var _data = JSON.parse(ShowBtn.getAttribute("content_data"));
        console.log(_data);
        SetTopicContent(_data.name, _data.content);
        //SetEditTeach(_data.name,_data.pass,_data.id,_data.tel,_data.school);
    });
    var ReviewBtn = MakeUpElement('a', "评阅  ", "tpl-table-black-operation-del");
    ReviewBtn.setAttribute('href', "javascript:;");
    ReviewBtn.setAttribute('data-am-modal', "{target: '#topic-review', closeViaDimmer: 0, width: 400, height: 300}");
    ReviewBtn.setAttribute("data-content", JSON.stringify({ id: _id, name: _name }));
    ReviewBtn.addEventListener('click', function () {
        _data = JSON.parse(ReviewBtn.getAttribute("data-content"));
        document.getElementById("topic_review_id").placeholder = _data.id;
        document.getElementById("topic_review_id").value = _data.id;

        document.getElementById("topic_review_name").placeholder = _data.name;
        document.getElementById("topic_review_name").value = _data.name;
    });
    var editIcon = MakeUpElement('i', '', "am-icon-pencil");
    var ReviewIcon = MakeUpElement('i', '', "am-icon-trash");
    ShowBtn.appendChild(editIcon);
    ReviewBtn.appendChild(ReviewIcon);
    //editBtn.appendChild(document.createTextNode(""));
    btnFather.appendChild(ShowBtn);
    //deleteBtn.appendChild(document.createTextNode(" 删除\n     "));
    btnFather.appendChild(ReviewBtn);
    btnRoot.appendChild(btnFather);
    temp.appendChild(name);
    temp.appendChild(id);
    //temp.appendChild(school);
    temp.appendChild(contact);
    temp.appendChild(btnRoot);
    return temp;
}


//state manager

function checkStateRender(currentstate) {
    var statestrs = document.getElementsByClassName("state-str");
    for (var i = 0; i < statestrs.length; i++) {
        var state = statestrs[i].getAttribute("statecode")
        if (state < currentstate) {
            statestrs[i].innerText = "已结束";
        } else if (state == currentstate) {
            statestrs[i].innerText = "进行中";
        } else {
            statestrs[i].innerText = "未开始";
        }
    }
    var statebtns = document.getElementsByClassName("state-btn");
    for (var i = 0; i < statebtns.length; i++) {
        var state = statebtns[i].getAttribute("statecode")
        if (state == currentstate + 1) {
            //statebtns[i].setAttribute("disabled","");
        } else {
            statebtns[i].setAttribute("disabled", "");
        }
    }
    console.log(statestrs);
}

//

function RefreshUngroup(teacharray, studentarray) {
    ClearRenderer(document.getElementById("ungroup_teacher"));
    ClearRenderer(document.getElementById("ungroup_student"));

    for (var i = 0; i < teacharray.length; i++) {
        document.getElementById("ungroup_teacher").appendChild(MakeUpUngroup(teacharray[i]));
    }
    for (var i = 0; i < studentarray.length; i++) {
        document.getElementById("ungroup_student").appendChild(MakeUpUngroup(studentarray[i]));
    }
}

function MakeUpUngroup(id) {
    var temp = MakeUpElement("tr", "", "gradeX");
    var content = MakeUpElement("td", id, "");
    temp.appendChild(content);
    return temp;
}

/**

<tr class="gradeX">
                                                    <td>1</td>
                                                    <td>111</td>
                                                    <td>老师</td>
                                                    
                                                </tr>

 */

function MakeUpGrouped(gid, id, type) {
    var temp = MakeUpElement("tr", "", "gradeX");
    var _type;
    if (type == 0) {
        _type = "教师";
    } else {
        _type = "学生";
    }
    var inner = "<td>" + gid + "</td>"
        + "<td>" + id + "</td>"
        + "<td>" + _type + "</td>";

    //temp.
    temp.innerHTML = inner;
    return temp;
}

function RefreshGrouped(grouparray) {
    ClearRenderer(document.getElementById("grouped_list"));
    for (var i = 0; i < grouparray.length; i++) {
        document.getElementById("grouped_list").appendChild(MakeUpGrouped(grouparray[i].gid, grouparray[i].id, grouparray[i].type))
    }
}

function GroupedItem(_gid, _id, _type) {
    this.gid = _gid;
    this.id = _id;
    this.type = _type;
}

function ArrangeNGroup(arr, numpergroup, type) {
    var TempLine = new Array();
    var currentGid = 1;
    var tempCount = 0;
    for (var i = 0; i < arr.length; i++) {
        var temp = new GroupedItem(currentGid, arr[i], type);
        TempLine.push(temp);
        tempCount++;
        if (tempCount > (numpergroup - 1)) {
            currentGid++;
            tempCount = 0;
        }
    }
    return TempLine;
}

function ArrangeStudentTeach(teacharray, studarray) {

    Shuffle(teacharray);
    Shuffle(studarray);
    var teachSize = teacharray.length;
    var stuSize = studarray.length;

    var groupnum = Math.ceil(teachSize / 2);
    var manpergroup = Math.ceil(stuSize / groupnum);

    var teachtempline = ArrangeNGroup(teacharray, 2, 0);
    var studtempline = ArrangeNGroup(studarray, manpergroup, 1);

    groupedFinalLine = teachtempline;

    for (var i = 0; i < studtempline.length; i++) {
        groupedFinalLine.push(studtempline[i]);
    }

    console.log(groupedFinalLine);
    RefreshGrouped(teachtempline);

}

function Shuffle(arr) {
    var len = arr.length;

    for (var i = len - 1; i >= 0; i--) {

        var randomIndex = Math.floor(Math.random() * (i + 1));

        var itemIndex = arr[randomIndex];
        arr[randomIndex] = arr[i];
        arr[i] = itemIndex;
    }
    return arr;
}

/*

<tr class="gradeX">
    <td>对于学生必须严格遵循选题规范的通知</td>
    <td>
        <div class="tpl-table-black-operation">
            <a href="javascript:;"
                data-am-modal="{target: '#show-msg',closeViaDimmer: 0, width: 600, height: 600}">
                <i class="am-icon-pencil"></i> 阅读
            </a>
            <a href="javascript:;" class="tpl-table-black-operation-del"
                data-am-modal="{target: '#del-msg',closeViaDimmer: 0, width: 400, height: 200}">
                <i class="am-icon-trash"></i> 删除
            </a>
        </div>
    </td>
</tr>

*/

function MakeUpMsg(mid, mtitle, mcontent, mtime) {
    var Root = MakeUpElement("tr", "", "gradeX");
    var title = MakeUpElement("td", mtitle, "");
    var time = MakeUpElement("td", mtime, "")
    var btnroot = MakeUpElement("td", "", "");
    var btngroup = MakeUpElement("div", "", "tpl-table-black-operation");
    var showbtn = MakeUpElement("a", "", "");
    showbtn.innerHTML = '<i class="am-icon-pencil"></i> 阅读';
    showbtn.setAttribute("data-am-modal", "{target: '#show-msg',closeViaDimmer: 0, width: 600, height: 600}");
    showbtn.setAttribute("data-content", JSON.stringify({
        title: mtitle,
        content: mcontent
    }));
    showbtn.addEventListener('click', () => {
        //设置msg信息
        var data = JSON.parse(showbtn.getAttribute("data-content"));
        $("#msg-title").val(data.title);
        $("#msg-content").val(data.content);
    });

    // var delbtn = MakeUpElement("a", "", "tpl-table-black-operation-del");
    // delbtn.innerHTML = '<i class="am-icon-trash"></i> 删除';
    // delbtn.setAttribute("data-am-modal", "{target: '#del-msg',closeViaDimmer: 0, width: 400, height: 200}");
    // delbtn.setAttribute("mid", mid);
    // delbtn.addEventListener('click', () => {
    //     //设置隐含msg id
    //     $("#del-msg").attr("mid", delbtn.getAttribute("mid"));
    // })

    btngroup.appendChild(showbtn);
    //btngroup.appendChild(delbtn);
    btnroot.appendChild(btngroup);
    Root.appendChild(title);
    Root.appendChild(time);
    Root.appendChild(btnroot);
    return Root;
}

function RefreshMsg(msgarr) {
    ClearRenderer(Msg_List_Render);
    for (var i = 0; i < msgarr.length; i++) {
        var temp = msgarr[i];
        Msg_List_Render.appendChild(MakeUpMsg(temp.messageid, temp.title, temp.content, temp.time));
    }
}

//==================================


function MakeUpSub(name, id, topic, content) {
    var Root = MakeUpElement("tr", "", "gradeX");
    var inner = '<td>' + name + '</id>'
        + '<td>' + id + '</id>'
        + '<td>' + topic + '</td>';
    Root.innerHTML = inner;
    var BtnRoot = MakeUpElement("td", "", "");
    var BtnGroup = MakeUpElement("div", "", "tpl-table-black-operation");
    var judgebtn = MakeUpElement("a", "", "");
    judgebtn.innerHTML = '<i class="am-icon-pencil"></i> 申请审批';
    judgebtn.setAttribute("data-am-modal", "{target: '#sub-judge',closeViaDimmer: 0, width: 600, height: 460}");
    judgebtn.setAttribute('content', content);
    judgebtn.setAttribute('id', id);
    judgebtn.addEventListener('click', function () {
        var content = judgebtn.getAttribute('content');
        $("#sub-content").val(content);
        document.getElementById("sub-content").setAttribute("sid", judgebtn.getAttribute("id"));
    });
    BtnGroup.appendChild(judgebtn);
    BtnRoot.appendChild(BtnGroup);
    Root.appendChild(BtnRoot);

    return Root;
}

function RefreshSub(subarr) {
    ClearRenderer(Sub_List_Render);
    for (var i = 0; i < subarr.length; i++) {
        var data = subarr[i];
        Sub_List_Render.appendChild(MakeUpSub(data.studentname, data.studentid, data.topicname, data.func))
    }
}