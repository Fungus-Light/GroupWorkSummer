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
        window.location.href = 'index.html';
})
}