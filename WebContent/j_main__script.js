

function check() {

	var classname = document.getElementById("class").value;
	classname.trim();
	if(classname == ""){
		alert("enter valid class name !!");
		document.getElementById("class").focus();
	}
	else{
		document.getElementById("maincode").innerHTML = "public class " + classname + "{\n\t public static void main(String[] args){ \n\n\n		} \n}" ;
	}
	
}


function  compile() {
	console.log("compiling");
	if(document.getElementById("maincode")==""){
		alert("Insert some code please !");
	}
	else{
		//code = maincode
		//classname = class
		var code=encodeURIComponent(document.getElementById("maincode").value);
		var url = "Compile?code=" + code + "&className=" + document.getElementById("class").value;
		
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
}


function run() {
	
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
}


function empty() {
	
	document.getElementById("class").value="";
  	document.getElementById("maincode").value="";
  	document.getElementById("output").value="";
	
}