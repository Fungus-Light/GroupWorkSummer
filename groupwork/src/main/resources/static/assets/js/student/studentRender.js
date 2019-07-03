var Topic_List_Render = document.getElementById('Topic_List_Render');

function SetPreUserInfo(data) {
    $("#user-id").val(data.userid);
    $("#user-password").val(data.password);
    $("#user-school").val(data.academic);
    $("#user-tel").val(data.tel);
    $("#user-name").val(data.name);
}

window.onload = function () {
    axios.post('/checkCookie').then(response => {
        if (response.data === 0) {
            window.location.href = 'login.html';
        }
    });

    axios.post('/showSingleStudent')
        .then(res => {
            document.getElementById("username-bar").innerHTML = '<i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>' + res.data.name + "同学";
            document.getElementById("username-head").innerText = res.data.name + "同学";
            SetPreUserInfo(res.data);
        })
        .catch(err => {
            console.error(err);
        });

    axios.post('/showAcademicTopicStudent')
        .then(res => {
            if (res.data[0].status == 0) {
                IfHasTopic(false);
                Refresh(res.data);

            } else {
                var data = res.data[0];
                SetTopicInfo(data.topic, data.topicid, data.academic, data.name, data.description);
                IfHasTopic(true);
            }
        })
        .catch(err => {
            console.error(err);
        })

    axios.post("/showMessage")
        .then(res => {
            var data = res.data;
            data.reverse();
            RefreshMsg(data)
        })
        .catch(err => {
            console.error(err);
        })

    axios.post("/showYourgroups")
        .then(res => {
            RefreshInGoup(res.data);
        })
        .catch(err => {
            console.error(err);
        })

}

function Refresh(topicarray) {
    ClearRenderer(Topic_List_Render);
    for (var i = 0; i < topicarray.length; i++) {
        var data = topicarray[i];
        AttachChildren(Topic_List_Render, MakeTopicStu(data.topic, data.topicid, data.name, data.description));
    }
}

function SetShowTopicBar(data) {
    $('#s-topic-name').val(data.name);
    $('#s-topic-brief').val(data.content);
}

function SetTopicInfo(name, id, school, teacher, content) {
    $('#topic-name').text(name);
    $('#topic-id').text(id);
    $('#topic-school').text(school);
    $('#topic-teach').text(teacher);
    $('#topic-content').attr("topic_data", JSON.stringify({
        name: name,
        content: content
    }));
}

function SetChooseTopicBar(data) {
    $('#topic-choose-name').val(data.name);
    $('#topic-choose-id').val(data.id);
}

/*

<tr>
    <td>大型购物商城</td>
    <td>Tp1100</td>
    <td>计算机学院</td>
    <td>章一迷</td>
    <td>
        <div class="am-btn-group am-btn-group-xs">
            <button
                class="am-btn am-btn-default am-btn-secondary"
                data-am-modal="{target: '#show-topic',closeViaDimmer: 0, width: 300, height: 300}">
                <span class="am-icon-anchor"></span> 详情</button>
            <button
            data-am-modal="{target: '#choose-topic',closeViaDimmer: 0, width: 300, height: 300}"
                class="am-btn am-btn-default am-btn-success"><span
                    class="am-icon-plus"></span> 选择</button>

        </div>

    </td>
</tr>


*/

function MakeTopicStu(_name, _id, _teach, _content) {
    var root = MakeUpElement('tr', "", "");
    var innertd = '<td>' + _name + '</td>'
        + '<td>' + _id + '</td>'

        + '<td>' + _teach + '</td>';
    var btnroot = MakeUpElement('td', "", "");
    var btngroup = MakeUpElement('div', "", "am-btn-group am-btn-group-xs");
    var showbtn = MakeUpElement('button', '', "am-btn am-btn-default am-btn-secondary topic-more");
    showbtn.innerHTML = '<span class="am-icon-anchor"></span> 详情';
    showbtn.setAttribute('data-am-modal', "{target: '#show-topic',closeViaDimmer: 0, width: 500, height: 400}");
    showbtn.setAttribute('topic_data', JSON.stringify({
        name: _name,
        content: _content
    }));
    showbtn.addEventListener('click', function () {
        var data = showbtn.getAttribute("topic_data");
        SetShowTopicBar(JSON.parse(data));
    });

    var addbtn = MakeUpElement('button', '', "am-btn am-btn-default am-btn-success topic-add");
    addbtn.innerHTML = '<span class="am-icon-plus"></span> 选择'
    addbtn.setAttribute('data-am-modal', "{target: '#choose-topic',closeViaDimmer: 0, width: 300, height: 300}");
    addbtn.setAttribute('topic_data', JSON.stringify({
        name: _name,
        id: _id
    }))
    addbtn.addEventListener('click', function () {
        var data = addbtn.getAttribute("topic_data");
        SetChooseTopicBar(JSON.parse(data));
    })

    btngroup.appendChild(showbtn);
    btngroup.appendChild(addbtn);
    btnroot.appendChild(btngroup);
    root.innerHTML = innertd;
    root.appendChild(btnroot);

    return root;

}

function MakeUpMsg(mtitle, mcontent, mtime) {
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
        Msg_List_Render.appendChild(MakeUpMsg(temp.title, temp.content, temp.time));
    }
}


function MakeUpInGroup(gid, uid, type) {
    var Root = MakeUpElement("tr", "", "");
    var _type;
    if (type == 0) {
        _type = "教师";
    } else {
        _type = "学生";
    }
    var inner = "<td>" + gid + "</td>"
        + "<td>" + uid + "</td>"
        + "<td>" + _type + "</td>";
    Root.innerHTML = inner;
    return Root;
}

var Group_Render = document.getElementById("Group_Render");
function RefreshInGoup(grouparr) {
    ClearRenderer(Group_Render);
    for (var i = 0; i < grouparr; i++) {
        var data = grouparr[i];
        Group_Render.appendChild(MakeUpInGroup(data.groupid, data.userid, data.identity))
    }
}