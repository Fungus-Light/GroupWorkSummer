/**
 * 控制渲染的通用方法
 */


function ClearRenderer(renderer) {
    renderer.innerText = "";
}

function AttachChildren(father, child) {
    father.appendChild(child);
}

function MakeUpElement(_tag,_text,_class){
    var temp=document.createElement(_tag);
    temp.innerText=_text;
    temp.setAttribute("class",_class);
    return temp;
}