function addManager() {
    var userid = $("#admin-id").value;
    var password = $("#admin-pass").value;
    var name = $("#admin-name").value;
    var tel = $("#admin-contact").value;
    axios.post('/addManager',{
        userid:userid,
        password:password,
        name:name,
        tel:tel
    }).then(response =>{
        location.reload();
})
}