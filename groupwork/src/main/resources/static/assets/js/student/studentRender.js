var Topic_List_Render=document.getElementById('Topic_List_Render');

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
            SetPreUserInfo(res.data);
        })
        .catch(err => {
            console.error(err);
        });
    
    axios.post('/showAcademicTopicStudent')
    .then(res => {
        if(res.data[0].status==0){
            IfHasTopic(false);
        Refresh(res.data);

        }else{
            var data=res.data[0];
            SetTopicInfo(data.topic, data.topicid,data.academic, data.name, data.description);
            IfHasTopic(true);
        }
    })
    .catch(err => {
        console.error(err);
    })
    
}

function Refresh(topicarray){
    ClearRenderer(Topic_List_Render);
    for(var i=0;i<topicarray.length;i++){
        var data=topicarray[i];
        AttachChildren(Topic_List_Render,MakeTopicStu(data.topic,data.topicid,data.name,data.description));
    }
}

function SetShowTopicBar(data){
    $('#s-topic-name').val(data.name);
    $('#s-topic-brief').val(data.content);
}

function SetTopicInfo(name, id, school, teacher, content) {
    $('#topic-name').text(name);
    $('#topic-id').text(id);
    $('#topic-school').text(school);
    $('#topic-teach').text(teacher);
    $('#topic-content').attr("topic_data",JSON.stringify({
        name:name,
        content:content
    }));
}

function SetChooseTopicBar(data){
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

function MakeTopicStu(_name,_id,_teach,_content) {
    var root=MakeUpElement('tr',"","");
    var innertd='<td>'+_name+'</td>'
                +'<td>'+_id+'</td>'

                +'<td>'+_teach+'</td>';
    var btnroot=MakeUpElement('td',"","");
    var btngroup=MakeUpElement('div',"","am-btn-group am-btn-group-xs");
    var showbtn=MakeUpElement('button','',"am-btn am-btn-default am-btn-secondary topic-more");
    showbtn.innerHTML='<span class="am-icon-anchor"></span> 详情';
    showbtn.setAttribute('data-am-modal',"{target: '#show-topic',closeViaDimmer: 0, width: 500, height: 400}");
    showbtn.setAttribute('topic_data',JSON.stringify({
        name:_name,
        content:_content
    }));
    showbtn.addEventListener('click', function () {
        var data=showbtn.getAttribute("topic_data");
        SetShowTopicBar(JSON.parse(data));
    });

    var addbtn=MakeUpElement('button','',"am-btn am-btn-default am-btn-success topic-add");
    addbtn.innerHTML='<span class="am-icon-plus"></span> 选择'
    addbtn.setAttribute('data-am-modal',"{target: '#choose-topic',closeViaDimmer: 0, width: 300, height: 300}");
    addbtn.setAttribute('topic_data',JSON.stringify({
        name:_name,
        id:_id
    }))
    addbtn.addEventListener('click',function(){
        var data=addbtn.getAttribute("topic_data");
        SetChooseTopicBar(JSON.parse(data));
    })

    btngroup.appendChild(showbtn);
    btngroup.appendChild(addbtn);
    btnroot.appendChild(btngroup);
    root.innerHTML=innertd;
    root.appendChild(btnroot);

    return root;

}