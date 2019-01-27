


<script>
function validateForm() {
    var fname = document.forms["myForm"]["fname"].value;
    var lname = document.forms["myForm"]["lname"].value;
    var email = document.forms["myForm"]["email"].value;
    var phone = document.forms["myForm"]["phone"].value;
    
    if (fname == "") {

    	document.getElementById('errorfname').innerHTML="*Please enter a First Name*";
        //alert("First Name must be filled out");
        return false;
    }
    else if (lname=="") {

  		//alert("Last Name must be filled out");
  		document.getElementById('errorlname').innerHTML="*Please enter a Last Name*";
        return false;
    }
    else if (email=="") {
document.getElementById('erroremail').innerHTML="*Please enter a Email*";
  		//alert("Email  must be filled out");
        return false;
    }

 

 if (validateEmail(email) && validatePhone(phone)&& validateName(fname)&& validateName(lname)) {
    


    return true;
  }
  else if(!validateName(fname))
  {
  	document.getElementById('errorfname').innerHTML="*Please enter a  valid First Name*";
  	 return false;
  }
  else if(!validateName(lname))
  {
  	document.getElementById('errorlname').innerHTML="*Please enter a  valid Last Name*";
  	 return false;
  }
  else if(!validateEmail(email))
  {
  	document.getElementById('erroremail').innerHTML="*Please enter a  valid Email*";
  	 return false;
  }
else if(!validatePhone(phone))
{
	document.getElementById('errorphone').innerHTML="*Please enter a  valid phone*";
  	 return false;
}




    function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function validatePhone(phone) {
  var re = /^([2-9][0-9]{2})[-]([0-9]{3})[-]([0-9]{4})$/;
  return re.test(phone);
}

function validateName(name) {
  var re = /^([a-zA-Z']+)$/;
  return re.test(name);
}



}
</script>

			<div class="column right">
				<div>
					<!-- <img class="resize2" src="images/heroroad.jpg" alt="heroroad.jpg"/> -->
					<img class="reshape" src="/phproot/CodeIgniter-3.1.9/images/pet store banner 5 png (1).png">
				</div>
				<article>
					<div class="divstyle">
						<h2>Service </h2>
						<p>Required information is marked with asterik (*).
						<section>
	<table class="tableclass">
		<form name="myForm"  action="service_action.php" method="post" novalidate  >
    <!-- <form name="myForm"  action="service_action.php" onsubmit="return validateForm()" method="post" novalidate  > -->
        <tbody cellpadding="20">
        	<input type="hidden" name="role" value="1" />
            <tr><td><label >*First Name:</label></td><td class="box1"  ><input id="fname" name="fname"  type="text" required /><span id="errorfname"></span></td></tr>
            <tr><td><label >*Last Name:</label></td><td class="box1"   ><input id="lname" name="lname" type = "text" required/><span id="errorlname"></span></td></tr>
            <tr><td><label >*E-mail:</label></td><td class="box1"   ><input id="email" name="email" type="email" required/><span id="erroremail"></span></td></tr>
            <tr><td><label >Phone:</label></td><td class="box1"   ><input id="phone" name="phone" type="tel" placeholder="123-456-7890"
           pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"/><span id="errorphone"></span></td></tr>
            <tr><td><label >Business Name:</label></td><td class="box1"   ><input name="businessname"  type = "text"></td></tr>
            <tr><td><input  type="submit" value="Submit"/> </td></tr>
        </tbody>
       
        </form>
    </table>

    <?php 
     $fullUrl= "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

    if(strpos($fullUrl,"signup=empty")==true){
    echo "<p style='color:Tomato;'> Please fill all mandatory fields!</p>";
    exit();
}
elseif (strpos($fullUrl,"signup=char")==true){
    echo "<p style='color:Tomato;' > First and last name should be character values</p>";
    exit();

}
elseif (strpos($fullUrl,"signup=length")==true){
    echo "<p style='color:Tomato;' > First and last name size should be between 5 to 15 character</p>";
    exit();

}
elseif (strpos($fullUrl,"signup=email")==true){
    echo "<p style='color:Tomato;'> Please provide email in correct format!!!</p>";
    exit();
}
elseif (strpos($fullUrl,"signup=phone")==true){
    echo "<p style='color:Tomato;'> Please provide correct format for phone number!</p>";
    exit();
}

elseif (strpos($fullUrl,"signup=bname")==true){
    echo "<p style='color:Tomato;'> Business name length should be between 5 to 15 charactors!</p>";
    exit();
}
elseif (strpos($fullUrl,"signup=success")==true){
    echo "<p style='color:Tomato;' >Service details added successfully</p>";
    exit();
}

elseif (strpos($fullUrl,"signup=userexist")==true){
    echo "<p style='color:Tomato;'>This email address is already present in the database!! </p>";
    exit();
}
 ?>
	

									
						
						</section>
						</div>
					