

function check() {

	var classname = document.getElementById("class").value;
	classname.trim();
	if(classname == ""){
		alert("enter valid file name !!");
		document.getElementById("class").focus();
	}
	
}


function  run() {
	console.log("compiling");
	if(document.getElementById("maincode")==""){
		alert("Insert some code please !");
	}
	else{
		//code = maincode
		//classname = class
		var code=encodeURIComponent(document.getElementById("maincode").value);
		var url = "PythCompile?code=" + code + "&className=" + document.getElementById("class").value;
		
		if(window.XMLHttpRequest){
           xmlhttp=new XMLHttpRequest();
        }
        else{
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }  

        xmlhttp.onreadystatechange=function(){
            if(xmlhttp.readyState==4 && xmlhttp.status==200){
            	document.getElementById("output").innerHTML=xmlhttp.responseText;                       
            }
        }
        xmlhttp.open("POST",url,true);
        xmlhttp.send();
	}
	console.log("compiled !!");
	console.log("executed !!");
}


/*function run() {
	
	var url = "Run?classname=" + document.getElementById("class").value;
	
	if(window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest();
     }
     else{
         xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
     }  

     xmlhttp.onreadystatechange=function(){
         if(xmlhttp.readyState==4 && xmlhttp.status==200){
         	document.getElementById("output").innerHTML=xmlhttp.responseText;                       
         }
     }
     xmlhttp.open("POST",url,true);
     xmlhttp.send();
}*/


function empty() {
	documet.getElementById("class").value="";
  	document.getElementById("maincode").value="";
  	document.getElementById("output").value="";
	
}