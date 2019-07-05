
$("#add-teach-test").click(function () {
    console.log("add teach")
    AttachChildren(Teacher_List_Render, MakeUpTeach("tnj", "kkk", "123456", "12345678", "SC"));
});

$("#add-stud-test").click(function () {
    console.log("add stud")
    AttachChildren(Student_List_Render, MakeUpStud("gkd", "kkk", "123456", "12345678", "SC"));
});
//$("#add-mission-text".click(function (){
//    console.log("add mission");
//   AttachChildren(Mission_List_Render,MakeUpMission(""));
//})

$("#add-topic").click(function () {
    console.log("add topic");
    AttachChildren(Topic_List_Render, MakeUpTopic("aaaa", "11111", "aaaaaa", "hhhhhhhhhh"));
});

//checkStateRender(3);

// for(var i=0;i<10;i++){
//     document.getElementById("ungroup_teacher").appendChild(MakeUpUngroup('111'));
//     document.getElementById("ungroup_student").appendChild(MakeUpUngroup('111'));

//     document.getElementById("grouped_list").appendChild(MakeUpGrouped("222","333",1))
//     console.log("!!!!!!")
// }

$("#testAddmsg").click(function(){
    $("#Msg_List_Render").get(0).appendChild(MakeUpMsg("111","222","333"));
});


var stu = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];
var teach = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'];

// $("#arrange-btn").click(function () {
//     ArrangeStudentTeach(teach,stu);
// })
// Shuffle(stu);
// Shuffle(teach)
// // console.log(stu);
// // console.log(teach);

// var stul = stu.length;
// var teachl = teach.length;
// var groupNum = Math.ceil(teachl / 2);
// var manperGroup = Math.ceil(stul / groupNum);

// //var finalline = new Array();

// var teachItemLine = ArrangeNGroup(teach,2,0);

// // var currentGid = 1;
// // var tempCount = 0
// // for (var i = 0; i < teachl; i++) {
// //     var temp = new GroupedItem(currentGid, teach[i], 0);
// //     teachItemLine.push(temp);
// //     tempCount++;
// //     if (tempCount > 1) {
// //         currentGid++;
// //         tempCount = 0;
// //     }
// // }

// console.log(teachItemLine);



// var stuItemLine = ArrangeNGroup(stu,manperGroup,1);
// // new Array();

// // currentGid = 1;
// // tempCount = 0;
// // for (var i = 0; i < stul; i++) {
// //     var temp = new GroupedItem(currentGid, stu[i], 1);
// //     stuItemLine.push(temp);
// //     tempCount++;
// //     if (tempCount > (manperGroup-1) ){
// //         currentGid++;
// //         tempCount = 0;
// //     }
// // }

// console.log(stuItemLine);


// // var stuItemLine=new Array();

// // for(var groupid=0;groupid<groupNum;groupid++){

// // }

// // //GroupedItem(_gid,_id,_type)
// // console.log(Math.ceil(teachl/2));
// // console.log(Math.ceil(stul/groupNum))
// // console.log("---------------------");
//
// for(var i=0;i<10;i++){
//     Sub_List_Render.appendChild(MakeUpSub("aaa","111","xxxx","sdsdsdsdsdsdsdsdsds"));
// }