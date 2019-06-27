function addTopic(){
    var title=$("#mission-name").val().toString();
    var content=$("#mission-brief").val().toString();

    if (title != null && content != null) {
        axios.post('/addTopic', {
            title: title,
            description:content
        }).then(response => {
            window.reload();
        })
    }
}

function editTopic(){
    var title=$("#e-mission-name").val().toString();
    var content=$("#e-mission-brief").val().toString();

    if (title != null && content != null) {
        axios.post('/addTopic', {
            title: title,
            description:content
        }).then(response => {
            window.reload();
        })
    }
}
//--------------------------


function updataInfo(){
    var phone=$("#user_phone").val().toString();
    var pass=$("#user_password").val().toString();

    if (phone != null && pass != null) {
        axios.post('/editTeacher', {
            tel: phone,
            password:pass
        }).then(response => {
            window.reload();
        })
    }

}