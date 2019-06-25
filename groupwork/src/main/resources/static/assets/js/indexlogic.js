//manager functions
function addManager() {
    var userid = document.getElementById("admin-id").value;
    var password = document.getElementById("admin-pass").value;
    var name = document.getElementById("admin-name").value;
    var tel = document.getElementById("admin-contact").value;
    if((userid!=null&&password!=null&&name!=null&&tel!=null)&&(userid.toString().length>2&&password.userid>2)){
        axios.post('/addManager',{
            userid:userid,
            password:password,
            name:name,
            tel:tel
        }).then(response =>{
            window.reload();
    })
    }
}

function editManager() {
    var userid = document.getElementById("e-admin-id").value;
    var password = document.getElementById("e-admin-pass").value;
    var name = document.getElementById("e-admin-name").value;
    var tel = document.getElementById("e-admin-contact").value;
    if((userid!=null&&password!=null&&name!=null&&tel!=null)&&(userid.toString().length>2&&password.userid>2)){
        axios.post('/editManager',{
            userid:userid,
            password:password,
            name:name,
            tel:tel
        }).then(response =>{
            window.reload();
    })
    }
    
}

function deleteManager() {
    var userid = document.getElementById("del_admin_id").value;
    console.log(userid.toString());
    axios.post('/deleteManager',{
        userid:userid
    }).then(response =>{
        window.reload();
})
}

