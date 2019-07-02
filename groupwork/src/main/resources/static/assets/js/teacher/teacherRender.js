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
var TopicResult_List_Render=document.getElementById("TopicResult_List_Render");

window.onload=function(){
    axios.post('/checkCookie').then(response=>{
        if(response.data === 0){
        window.location.href = 'login.html';
    }
    });

    axios.post('/showTeacherTopic').then(response=>{
        // console.log(response.data);
        RefreshTopic(response.data);
    });

    axios.post('/showSingleTeacher').then(response=>{
        document.getElementById("username-bar").innerHTML='<i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>'+response.data.name+"老师";
        document.getElementById("username-head").innerText=response.data.name+"老师";
        presetInfo(response.data);
    });

    //the topiced stu
    axios.post('showStudentBySingleTeacher')
    .then(res => {
        RefreshTopicStu(res.data);
    })
    .catch(err => {
        console.error(err); 
    })
    
}

function presetInfo(data){
    $("#user-name").val(data.name);
    $("#user_password").val(data.password);
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
    //
    // var editBtn = MakeUpElement('a', "", "");
    // editBtn.setAttribute('href', "javascript:;");
    // editBtn.setAttribute("content_data", JSON.stringify({ title: _title, id: _id, status: _status }));
    // editBtn.setAttribute('data-am-modal', "{target: '#edit-mission',closeViaDimmer: 0, width: 600, height: 460}");
    // editBtn.innerHTML = '<i class="am-icon-pencil"></i>编辑';
    // editBtn.addEventListener('click', function () {
    //     SetEditTopic();
    //     var _data = JSON.parse(this.getAttribute("content_data"));
    //     //console.log(_data);
    //     SetEditTopic(_data.title);
    // });

    var deleteBtn = MakeUpElement('a', "", "tpl-table-black-operation-del");
    deleteBtn.setAttribute('href', "javascript:;");
    deleteBtn.setAttribute('data-am-modal', "{target: '#del-mission', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("content_data", _id);
    deleteBtn.innerHTML = '<i class="am-icon-trash"></i>删除 ';
    deleteBtn.addEventListener('click', function () {
        $("#del_topic_id").val(deleteBtn.getAttribute("content_data"));
    });

    //btnGroup.appendChild(editBtn);
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
        AttachChildren(Topic_List_Render, MakeUpTopic(topicarray[i].topic, topicarray[i].topicid,status));
    }
}

//topic-stud
/*

<tr class="gradeX">
        <td>李二狗</id>
        <td>2015424275589</id>
        <td>大型购物商城</td>
        <td>
            <div class="tpl-table-black-operation">
                <a href="javascript:;">
                    下载附件
                </a>
            </div>
        </td>
        <td>
            <div class="tpl-table-black-operation">
                <a href="javascript:;"
                data-am-modal="{target: '#add-guide',closeViaDimmer: 0, width: 600, height: 460}">
                    <i class="am-icon-pencil"></i> 添加指导
                </a>
                <a href="javascript:;">
                    <i class="am-icon-pencil"></i> 指导记录
                </a>

            </div>
        </td>

</tr>

*/

function RefreshTopicStu(topicarray){
    ClearRenderer(TopicResult_List_Render);
    for(var i=0;i<topicarray.length;i++){
        var temp=topicarray[i];
        TopicResult_List_Render.appendChild(MakeUpTopicStu(temp.name,temp.studentid,temp.title,temp.record));
    }
}

function SetAddGuideID(id){
    document.getElementById("add-guide").setAttribute("user-id",id);
}

function MakeUpTopicStu(_name,_userid,_topic,_guidelist){
    var Root=MakeUpElement("tr","","gradeX");
    var inner="<td>"+_name+"</id>"
        +"<td>"+_userid+"</id>"
        +"<td>"+_topic+"</td>";
    Root.innerHTML=inner;
    var downloadbtnroot=MakeUpElement("td","","");
    var downbtngroup=MakeUpElement("div","","tpl-table-black-operation");
    var downbtn=MakeUpElement("a","下载附件","");
    downbtn.setAttribute("data-content",JSON.stringify({
        userid:_userid
    }))
    downbtn.addEventListener('click',function(){
        console.log("down it");
    })
    downbtngroup.appendChild(downbtn);
    downloadbtnroot.appendChild(downbtngroup);

    var actionbtnroot=MakeUpElement("td","","");
    var actionbtngroup=MakeUpElement("div","","tpl-table-black-operation");
    var addguidebtn=MakeUpElement("a","","");
    addguidebtn.innerHTML='<i class="am-icon-pencil"></i> 添加指导';
    addguidebtn.setAttribute("data-content",JSON.stringify({
        userid:_userid
    }))
    addguidebtn.setAttribute("data-am-modal","{target: '#add-guide',closeViaDimmer: 0, width: 600, height: 460}");
    addguidebtn.addEventListener('click',()=>{
        SetAddGuideID(JSON.parse(addguidebtn.getAttribute("data-content")).userid);
    })
    var viewbtn=MakeUpElement("a","","");
    viewbtn.setAttribute("data-am-modal","{target: '#his-guide',closeViaDimmer: 0, width: 600, height: 460}");
    viewbtn.innerHTML='<i class="am-icon-pencil"></i> 指导记录';
    viewbtn.setAttribute("data-content",JSON.stringify(_guidelist));
    actionbtngroup.appendChild(addguidebtn);
    actionbtngroup.appendChild(viewbtn);
    actionbtnroot.appendChild(actionbtngroup);

    Root.appendChild(downloadbtnroot);
    Root.appendChild(actionbtnroot);

    return Root;
}