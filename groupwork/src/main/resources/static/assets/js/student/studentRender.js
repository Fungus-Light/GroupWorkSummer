function SetPreUserInfo(data){
    $("#user-id").val(data.id);
    $("#user-password").val(data.password);
    $("#user-school").val(data.academic);
    $("#user-tel").val(data.tel);
    $("#user-name").val(data.name);
}

window.onload=function(){
    axios.post('/showSingleStudent')
    .then(res => {
        SetPreUserInfo(res.data);
    })
    .catch(err => {
        console.error(err); 
    })
}

