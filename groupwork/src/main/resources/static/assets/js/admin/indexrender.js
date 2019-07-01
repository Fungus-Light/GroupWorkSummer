/**
 *  这个是管理员页面控制渲染的脚本
 * 
 */

var Admin_List_Render = document.getElementById("Admin_List_Render");
var Teacher_List_Render=document.getElementById("Teacher_List_Render");
var Student_List_Render=document.getElementById("Student_List_Render");

window.onload=function(){
    axios.post('/checkCookie').then(response=>{
        if(response.data === 0){
        window.location.href='login.html';
    }

    });
    axios.post('/showManager').then(response=>{
        //console.log(response.data);
        RefreshAdiminlist(response.data);
    });

    axios.post('/showTeacher').then(response=>{
        RefreshTeachlist(response.data);
    });

    axios.post('/showStudent').then(response=>{
        RefreshStudlist(response.data);
    });
}

function RefreshAdiminlist(adminarray){
    ClearRenderer(Admin_List_Render);
    for(var i=0;i<adminarray.length;i++){
        AttachChildren(Admin_List_Render,MakeUpAdmin(adminarray[i].name,adminarray[i].password,adminarray[i].userid,adminarray[i].tel));
    }
}

function RefreshTeachlist(teacharray){
    ClearRenderer(Teacher_List_Render);
    for(var i=0;i<teacharray.length;i++){
        AttachChildren(Teacher_List_Render,MakeUpTeach(teacharray[i].name,teacharray[i].password,teacharray[i].userid,teacharray[i].tel,teacharray[i].academic));
    }
}

function RefreshStudlist(studarray){
    ClearRenderer(Student_List_Render);
    for(var i=0;i<studarray.length;i++){
        AttachChildren(Student_List_Render,MakeUpStud(studarray[i].name,studarray[i].password,studarray[i].userid,studarray[i].tel,studarray[i].academic));
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

function CleanAdminEdit(){
    document.getElementById("e-admin-id").value="";
    document.getElementById("e-admin-pass").value="";
    document.getElementById("e-admin-name").value="";
    document.getElementById("e-admin-contact").value="";
}

function SetEditAdmin(name,pass,id,tel){
    document.getElementById("e-admin-id").value=id;
    document.getElementById("e-admin-pass").value=pass;
    document.getElementById("e-admin-name").value=name;
    document.getElementById("e-admin-contact").value=tel;
}

function SetEditTeach(name,pass,id,tel,school){
    document.getElementById("e-teach-id").value=id;
    document.getElementById("e-teach-pass").value=pass;
    document.getElementById("e-teach-name").value=name;
    document.getElementById("e-teach-contact").value=tel;
    document.getElementById("e-teach-school").value=school;
}

function SetEditStud(name,pass,id,tel,school){
    document.getElementById("e-stud-id").value=id;
    document.getElementById("e-stud-pass").value=pass;
    document.getElementById("e-stud-name").value=name;
    document.getElementById("e-stud-contact").value=tel;
    document.getElementById("e-stud-school").value=school;
}

function MakeUpAdmin(_name,_pass,_id,_contact,_school) {
    var temp=MakeUpElement("tr","","gradeX");
    
    var name=MakeUpElement('td',_name,"");
    var id=MakeUpElement('td',_id,"");
    var academic=MakeUpElement('td',_school,"");
    var contact=MakeUpElement('td',_contact,"");
    var btnRoot=MakeUpElement('td',"","");
    var btnFather=MakeUpElement('div',"","tpl-table-black-operation");
    var editBtn=MakeUpElement("a","编辑  ","");
    editBtn.setAttribute('href',"javascript:;");
    editBtn.setAttribute("content_data",JSON.stringify({name:_name,pass:_pass,id:_id,tel:_contact,school:_school}));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal',"{target: '#edit-admin',closeViaDimmer: 0, width: 600, height: 500}");
    editBtn.addEventListener('click',function(){
        //console.log("edit");
        //CleanAdminEdit();
        var _data=JSON.parse(this.getAttribute("content_data"));
        //console.log(_data);
        SetEditAdmin(_data.name,_data.pass,_data.id,_data.tel,_data.school);
    });
    var deleteBtn=MakeUpElement('a',"删除  ","tpl-table-black-operation-del");
    deleteBtn.setAttribute('href',"javascript:;");
    deleteBtn.setAttribute('data-am-modal',"{target: '#del-admin', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("data-contentr",_id);
    deleteBtn.addEventListener('click',function(){
        document.getElementById("del_admin_id").placeholder=deleteBtn.getAttribute("data-contentr");
        document.getElementById("del_admin_id").value=deleteBtn.getAttribute("data-contentr");
    });
    var editIcon=MakeUpElement('i','',"am-icon-pencil");
    var deleteIcon=MakeUpElement('i','',"am-icon-trash");
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

function MakeUpTeach(_name,_pass,_id,_contact,_school) {
    var temp=MakeUpElement("tr","","gradeX");
    
    var name=MakeUpElement('td',_name,"");
    var id=MakeUpElement('td',_id,"");
    var school=MakeUpElement('td',_school,"");
    var contact=MakeUpElement('td',_contact,"");
    var btnRoot=MakeUpElement('td',"","");
    var btnFather=MakeUpElement('div',"","tpl-table-black-operation");
    var editBtn=MakeUpElement("a","编辑  ","");
    editBtn.setAttribute('href',"javascript:;");
    editBtn.setAttribute("content_data",JSON.stringify({name:_name,pass:_pass,id:_id,tel:_contact,school:_school}));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal',"{target: '#edit-teach',closeViaDimmer: 0, width: 600, height: 600}");
    editBtn.addEventListener('click',function(){
        console.log("edit");
        //CleanAdminEdit();
        var _data=JSON.parse(editBtn.getAttribute("content_data"));
        console.log(_data);
        SetEditTeach(_data.name,_data.pass,_data.id,_data.tel,_data.school);
    });
    var deleteBtn=MakeUpElement('a',"删除  ","tpl-table-black-operation-del");
    deleteBtn.setAttribute('href',"javascript:;");
    deleteBtn.setAttribute('data-am-modal',"{target: '#del-teach', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("data-contentr",_id);
    deleteBtn.addEventListener('click',function(){
        document.getElementById("del_teach_id").placeholder=deleteBtn.getAttribute("data-contentr");
        document.getElementById("del_teach_id").value=deleteBtn.getAttribute("data-contentr");
    });
    var editIcon=MakeUpElement('i','',"am-icon-pencil");
    var deleteIcon=MakeUpElement('i','',"am-icon-trash");
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

function MakeUpStud(_name,_pass,_id,_contact,_school) {
    var temp=MakeUpElement("tr","","gradeX");
    
    var name=MakeUpElement('td',_name,"");
    var id=MakeUpElement('td',_id,"");
    var school=MakeUpElement('td',_school,"");
    var contact=MakeUpElement('td',_contact,"");
    var btnRoot=MakeUpElement('td',"","");
    var btnFather=MakeUpElement('div',"","tpl-table-black-operation");
    var editBtn=MakeUpElement("a","编辑  ","");
    editBtn.setAttribute('href',"javascript:;");
    editBtn.setAttribute("content_data",JSON.stringify({name:_name,pass:_pass,id:_id,tel:_contact,school:_school}));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal',"{target: '#edit-stud',closeViaDimmer: 0, width: 600, height: 600}");
    editBtn.addEventListener('click',function(){
        console.log("edit stud");
        
        var _data=JSON.parse(this.getAttribute("content_data"));
        console.log(_data);
        SetEditStud(_data.name,_data.pass,_data.id,_data.tel,_data.school);
    });
    var deleteBtn=MakeUpElement('a',"删除  ","tpl-table-black-operation-del");
    deleteBtn.setAttribute('href',"javascript:;");
    deleteBtn.setAttribute('data-am-modal',"{target: '#del-stud', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("data-contentr",_id);
    deleteBtn.addEventListener('click',function(){
        document.getElementById("del_stud_id").placeholder=deleteBtn.getAttribute("data-contentr");
        document.getElementById("del_stud_id").value=deleteBtn.getAttribute("data-contentr");
    });
    var editIcon=MakeUpElement('i','',"am-icon-pencil");
    var deleteIcon=MakeUpElement('i','',"am-icon-trash");
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

function MakeUpTopic(_name,_id,_school,_teach,_content) {
    var temp=MakeUpElement("tr","","gradeX");
    
    var name=MakeUpElement('td',_name,"");
    var id=MakeUpElement('td',_id,"");
    var school=MakeUpElement('td',_school,"");
    var contact=MakeUpElement('td',_teach,"");
    var btnRoot=MakeUpElement('td',"","");
    var btnFather=MakeUpElement('div',"","tpl-table-black-operation");
    var editBtn=MakeUpElement("a","详情  ","");
    editBtn.setAttribute('href',"javascript:;");
    editBtn.setAttribute("content_data",JSON.stringify({name:_name,id:_id,school:_school,teach:_teach,content:_content}));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal',"{target: '#edit-teach',closeViaDimmer: 0, width: 600, height: 600}");
    editBtn.addEventListener('click',function(){
        console.log("edit");
        //CleanAdminEdit();
        var _data=JSON.parse(editBtn.getAttribute("content_data"));
        console.log(_data);
        //SetEditTeach(_data.name,_data.pass,_data.id,_data.tel,_data.school);
    });
    var deleteBtn=MakeUpElement('a',"评阅  ","tpl-table-black-operation-del");
    deleteBtn.setAttribute('href',"javascript:;");
    deleteBtn.setAttribute('data-am-modal',"{target: '#del-teach', closeViaDimmer: 0, width: 400, height: 225}");
    deleteBtn.setAttribute("data-content",_id);
    deleteBtn.addEventListener('click',function(){
        document.getElementById("del_teach_id").placeholder=deleteBtn.getAttribute("data-content");
        document.getElementById("del_teach_id").value=deleteBtn.getAttribute("data-content");
    });
    var editIcon=MakeUpElement('i','',"am-icon-pencil");
    var deleteIcon=MakeUpElement('i','',"am-icon-trash");
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
