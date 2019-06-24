//$("#add-admin-submit").click(addManager);
//document.getElementById("add-admin-submit").addEventListener('click',addManager);
//
// function addManager() {console.log("hello");
//     var userid = document.getElementById("admin-id").value;
//     var password = document.getElementById("admin-pass").value;
//     var name = document.getElementById("admin-name").value;
//     var tel = document.getElementById("admin-contact").value;
//     axios.post('/addManager',{
//         userid:userid,
//         password:password,
//         name:name,
//         tel:tel
//     }).then(response =>{
//        location.reload();
// })
// }

function addManager() {
    var userid = document.getElementById("admin-id").value;
    var password = document.getElementById("admin-pass").value;
    var name = document.getElementById("admin-name").value;
    var tel = document.getElementById("admin-contact").value;
    axios.post('/addManager',{
        userid:userid,
        password:password,
        name:name,
        tel:tel
    }).then(response =>{
        window.reload();
})
}

function editManager() {
    var userid = document.getElementById("e-admin-id").value;
    var password = document.getElementById("e-admin-pass").value;
    var name = document.getElementById("e-admin-name").value;
    var tel = document.getElementById("e-admin-contact").value;
    axios.post('/editManager',{
        userid:userid,
        password:password,
        name:name,
        tel:tel
    }).then(response =>{
        window.reload();
})
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

$(".nvl").click(function(){
    $(".nvl").children('a').attr("class","");
    $(".rbd").hide();
    $(this).children('a').attr('class',"active");
    var key= $(this).attr("call");
    console.log(key);
    console.log($(".rdb[callid='"+key+"']"));
    
    $("div[callid='"+key+"']").show();
})