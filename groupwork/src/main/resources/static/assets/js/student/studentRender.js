function SetPreUserInfo(data){
    $("#user-id").val(data.userid);
    $("#user-password").val(data.password);
    $("#user-school").val(data.academic);
    $("#user-tel").val(data.tel);
    $("#user-name").val(data.name);
}

window.onload=function(){
    axios.post('/checkCookie').then(response=>{
        if(response.data === 0){
        window.location.href = 'login.html';
    }
    });

    axios.post('/showSingleStudent')
    .then(res => {
        SetPreUserInfo(res.data);
    })
    .catch(err => {
        console.error(err); 
    })
}

