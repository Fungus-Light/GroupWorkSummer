function updateStudentInfo(){
    var id=$("#user-id").val();
    var password=$("#user-password").val();
    var academic=$("#user-school").val();
    var tel=$("#user-tel").val();
    var name=$("#user-name").val();

    if(password!=null&&tel!=null){
        axios.post('editStudent',{
            userid:id,
            password:password,
            academic:academic,
            tel:tel,
            name:name
        })
        .then(res => {
            window.location.reload();
        })
        .catch(err => {
            console.error(err); 
        })
    }
}