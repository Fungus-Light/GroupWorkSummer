
var Admin_List_Render = document.getElementById("Admin_List_Render");
var testAddadmin=document.getElementById("testAddadmin");
testAddadmin.addEventListener('click',function(){
    AttachChildren(Admin_List_Render,MakeUpAdmin("111","222","123456"));
})

window.onload=function(){
    axios.post('/showManager').then(response=>{
        console.log(response.data);
        RefreshAdiminlist(response.data);
    });
}

function ClearRenderer(renderer) {
    renderer.innerText = "";
}

function AttachChildren(father, child) {
    father.appendChild(child);
}

function RefreshAdiminlist(adminarray){
    ClearRenderer(Admin_List_Render);
    for(var i=0;i<adminarray.length;i++){
        AttachChildren(Admin_List_Render,MakeUpAdmin(adminarray[i].name,adminarray[i].userid,adminarray[i].tel));
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

function MakeUpElement(_tag,_text,_class){
    var temp=document.createElement(_tag);
    temp.innerText=_text;
    temp.setAttribute("class",_class);
    return temp;
}

function CleanAdminEdit(){
    document.getElementById("e-admin-id").value="";
    document.getElementById("e-admin-pass").value="";
    document.getElementById("e-admin-name").value="";
    document.getElementById("e-admin-contact").value="";
}

function SetEditAdmin(name,id,tel){
    document.getElementById("e-admin-id").value=id;
    document.getElementById("e-admin-pass").value="";
    document.getElementById("e-admin-name").value=name;
    document.getElementById("e-admin-contact").value=tel;
}

function MakeUpAdmin(_name,_id,_contact) {
    var temp=MakeUpElement("tr","","gradeX");
    
    var name=MakeUpElement('td',_name,"");
    var id=MakeUpElement('td',_id,"");
    var contact=MakeUpElement('td',_contact,"");
    var btnRoot=MakeUpElement('td',"","");
    var btnFather=MakeUpElement('div',"","tpl-table-black-operation");
    var editBtn=MakeUpElement("a","编辑  ","");
    editBtn.setAttribute('href',"javascript:;");
    editBtn.setAttribute("content_data",JSON.stringify({name:_name,id:_id,tel:_contact}));
    //data-am-modal="{target: '#add-admin',closeViaDimmer: 0, width: 600, height: 500}"
    editBtn.setAttribute('data-am-modal',"{target: '#edit-admin',closeViaDimmer: 0, width: 600, height: 500}");
    editBtn.addEventListener('click',function(){
        console.log("edit");
        CleanAdminEdit();
        var _data=JSON.parse(this.getAttribute("content_data"));
        console.log(_data);
        SetEditAdmin(_data.name,_data.id,_data.tel);
    });
    var deleteBtn=MakeUpElement('a',"删除  ","tpl-table-black-operation-del");
    deleteBtn.setAttribute('href',"javascript:;");
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
    temp.appendChild(contact);
    temp.appendChild(btnRoot);
    return temp;
}