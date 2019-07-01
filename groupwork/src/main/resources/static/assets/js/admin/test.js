
$("#add-teach-test").click(function () { 
    console.log("add teach")
    AttachChildren(Teacher_List_Render,MakeUpTeach("tnj","kkk","123456","12345678","SC"));
});

$("#add-stud-test").click(function () { 
    console.log("add stud")
    AttachChildren(Student_List_Render,MakeUpStud("gkd","kkk","123456","12345678","SC"));
});
//$("#add-mission-text".click(function (){
//    console.log("add mission");
//   AttachChildren(Mission_List_Render,MakeUpMission(""));
//})

$("#add-topic").click(function(){
    console.log("add topic");
    AttachChildren(Topic_List_Render,MakeUpTopic("aaaa","11111","aaaaaa","hhhhhhhhhh"));
});

//checkStateRender(3);

// for(var i=0;i<10;i++){
//     document.getElementById("ungroup_teacher").appendChild(MakeUpUngroup('111'));
//     document.getElementById("ungroup_student").appendChild(MakeUpUngroup('111'));

//     document.getElementById("grouped_list").appendChild(MakeUpGrouped("222","333",1))
//     console.log("!!!!!!")
// }