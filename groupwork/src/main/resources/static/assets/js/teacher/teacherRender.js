/**
这个是教师渲染脚本
*/

/*

<tr>
    <td>大型购物商城</td>
    <td>"CS"+Math.random()</td>
    <td>计算机学院</td>
    <td>章一迷</td>
    <td>题目审核阶段</td>
    <td>
        <div class="tpl-table-black-operation">
            <a href="javascript:;">
                <i class="am-icon-pencil"></i>编辑
            </a> 
            <a href="javascript:;" class="tpl-table-black-operation-del">
                <i class="am-icon-trash"></i>删除 
            </a>
        </div>
    </td>
</tr>

*/
var Topic_List_Render = $("#Topic_List_Render").get(0);

window.onload=function(){
    axios.post('/checkCookie').then(response=>{
        if(response.data === 0){
        window.location.href = 'login.html';
    }
    });

    axios.post('/showTeacherTopic').then(response=>{
        console.log(response.data);
        RefreshTopic(response.data);
    });

    // axios.post('/showTeacher').then(response=>{
    //     presetInfo(response.data);
    // });
    
}

function presetInfo(data){
    $("#user-name").val(data.name);
    $("#user_id").val(data.userid);
    $("#user_school").val(data.academic);
    $("#user_phone").val(data.tel);
}

function SetEditTopic(_title) {
    $("#e-mission-name").val(_title);
}

function MakeUpTopic(_title, _id, _status) {
    var root = MakeUpElement('tr', "", "");
    //  'data-am-modal',"{target: '#edit-admin',closeViaDimmer: 0, width: 600, height: 500}"
    var inner = "<td>" + _title + "</td>"
        + "<td>" + _id + "</td>"
        + "<td>" + _status + "</td>"

    var btnRoot = MakeUpElement("td", "", "");
    var btnGroup = MakeUpElement('div', "", "tpl-table-black-operation");

    var editBtn = MakeUpElement('a', "", "");
    editBtn.setAttribute('href', "javascript:;");
    editBtn.setAttribute("content_data", JSON.stringify({ title: _title, id: _id, status: _status }));
    editBtn.setAttribute('data-am-modal', "{target: '#edit-mission',closeViaDimmer: 0, width: 600, height: 460}");
    editBtn.innerHTML = '<i class="am-icon-pencil"></i>编辑';
    editBtn.addEventListener('click', function () {
        SetEditTopic();
        var _data = JSON.parse(this.getAttribute("content_data"));
        //console.log(_data);
        SetEditTopic(_data.title);
    });

    var deleteBtn = MakeUpElement('a', "", "tpl-table-black-operation-del");
    deleteBtn.setAttribute('href', "javascript:;");
    deleteBtn.setAttribute('data-am-modal', "{target: '#del-mission', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("content_data", _id);
    deleteBtn.innerHTML = '<i class="am-icon-trash"></i>删除 ';
    deleteBtn.addEventListener('click', function () {
        $("#del_topic_id").val(deleteBtn.getAttribute("content_data"));
    });

    btnGroup.appendChild(editBtn);
    btnGroup.appendChild(deleteBtn);
    btnRoot.appendChild(btnGroup);
    root.innerHTML = inner;
    root.appendChild(btnRoot);
    return root;
}

function RefreshTopic(topicarray) {
    ClearRenderer(Topic_List_Render);
    //0 待审核 1 pass 2 refuse
    for (var i = 0; i < topicarray.length; i++) {
        var status = "error"
        switch (topicarray[i].state) {
            case 0:
                status = "待审核";
                break;
            case 1:
                status = "审核通过";
                break;
            case 2:
                status = "被拒绝";
                break;
        }
        AttachChildren(Topic_List_Render, MakeUpTopic(topicarray[i].title, topicarray[i].topicid,status));
    }
}