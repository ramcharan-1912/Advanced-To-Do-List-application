let tasks = JSON.parse(localStorage.getItem("tasks")) || [];

function saveTasks(){
localStorage.setItem("tasks", JSON.stringify(tasks));
}

function renderTasks(list=tasks){

let taskList=document.getElementById("taskList");
taskList.innerHTML="";

list.forEach((task,index)=>{

let li=document.createElement("li");
li.classList.add(task.priority);

let span=document.createElement("span");
span.innerHTML="📌 "+task.text+" | 📅 "+task.date;

if(task.completed){
span.classList.add("completed");
}

span.onclick=()=>{
task.completed=!task.completed;
saveTasks();
renderTasks();
};

let actions=document.createElement("div");
actions.className="actions";

let edit=document.createElement("button");
edit.textContent="Edit";
edit.className="edit";

edit.onclick=()=>{
let newTask=prompt("Edit task",task.text);
if(newTask){
tasks[index].text=newTask;
saveTasks();
renderTasks();
}
};

let del=document.createElement("button");
del.textContent="Delete";
del.className="delete";

del.onclick=()=>{
tasks.splice(index,1);
saveTasks();
renderTasks();
};

actions.appendChild(edit);
actions.appendChild(del);

li.appendChild(span);
li.appendChild(actions);

taskList.appendChild(li);

});

updateStats();

}

function addTask(){

let text=document.getElementById("taskInput").value.trim();
let priority=document.getElementById("priority").value;
let date=document.getElementById("dueDate").value;

if(text===""){
alert("Enter task");
return;
}

tasks.push({
text:text,
priority:priority,
date:date,
completed:false
});

document.getElementById("taskInput").value="";
document.getElementById("dueDate").value="";

saveTasks();
renderTasks();

}

function searchTask(){

let keyword=document.getElementById("search").value.toLowerCase();

let filtered=tasks.filter(task=>
task.text.toLowerCase().includes(keyword)
);

renderTasks(filtered);

}

function updateStats(){

let total=tasks.length;
let completed=tasks.filter(t=>t.completed).length;

document.getElementById("total").innerText=total;
document.getElementById("completed").innerText=completed;

let percent=total ? (completed/total)*100 : 0;
document.getElementById("progress").style.width=percent+"%";

}

function sortPriority(){

let order={high:1,medium:2,low:3};

tasks.sort((a,b)=>order[a.priority]-order[b.priority]);

saveTasks();
renderTasks();

}

function clearCompleted(){

tasks=tasks.filter(task=>!task.completed);

saveTasks();
renderTasks();

}

function toggleDarkMode(){
document.body.classList.toggle("dark");
}

document.getElementById("taskInput").addEventListener("keypress",function(e){
if(e.key==="Enter"){
addTask();
}
});

renderTasks();