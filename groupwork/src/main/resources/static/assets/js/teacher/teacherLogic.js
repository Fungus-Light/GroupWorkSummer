function addTopic(){

    console.log("------------")
    var title=$("#mission-name").val().toString();
    var content=$("#mission-brief").val().toString();
    console.log(title);
    if (title != null && content != null) {
        axios.post('/addTopic', {
            topic: title,
            description:content
        }).then(response=>{
            window.reload();
            // console.log(response.data)
        })
    }
}

function editTopic(){
    var title=$("#e-mission-name").val().toString();
    var content=$("#e-mission-brief").val().toString();

    if (title != null && content != null) {
        axios.post('/addTopic', {
            topic: title,
            description:content
        }).then(response => {
            //window.reload();
        })
    }
}
//--------------------------


function updataInfo(){
    var name= $("#user-name").val();
    var password= $("#user_password").val();
    var userid= $("#user_id").val();
    var academic= $("#user_school").val();
    var tel= $("#user_phone").val();

    if (userid != null && password != null) {
        axios.post('/editTeacher', {
            userid:userid,
            name:name,
            academic:academic,
            tel:tel,
            password:password
        }).then(response => {
            window.reload();
        })
    }

}