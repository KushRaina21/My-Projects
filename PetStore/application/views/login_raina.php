

<script>
function validateForm() {
    
    var email = document.forms["myForm"]["email"].value;
    var password = document.forms["myForm"]["password"].value;
    
    
    if (email=="") {
document.getElementById('erroremail').innerHTML="*Please enter a Email*";
  		//alert("Email  must be filled out");
        return false;
    }


    else if(password=="")
    {

    	document.getElementById('erroremail').innerHTML="*Please enter Password*";
  		//alert("Email  must be filled out");
        return false;
    }

  if (validateEmail(email) &&  validatePassword(password)) {
    


    return true;
  }
  else if(!validateEmail(email))
  {
  	document.getElementById('erroremail').innerHTML="*Please enter a  valid Email*";
  	 return false;
  }
else if(!validatePassword(password))
{
	document.getElementById('errorpassword').innerHTML="*Please enter a  valid password*";
  	 return false;
}




    function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function validatePassword(password) {
  var re = /[a-zA-Z0-9]{8}/;
  return re.test(password);
}




}
</script>



			<div class="column right">
				<div>
					<!-- <img class="reshape" src="images/heroroad.jpg" alt="heroroad.jpg"/> -->
					<img class="reshape" src="/phproot/CodeIgniter-3.1.9/images/pet store banner 5 png (1).png">
				</div>
				<article>
					<div class="divstyle">
						<h2>Login </h2>
						<p>Required information is marked with asterik (*).
						<section>
	<table class="tableclass">

		<!-- <form name="myForm"   action="login_action.php" onsubmit="return validateForm()" method="post" novalidate > -->
		<form name="myForm"   action="login_action.php"  method="post" novalidate >	
        <tbody cellpadding="20">
        	 
			 <tr><td><label >*Email:</label></td><td class="box1" ><input id="email" name="email" type="email" required/><span id="erroremail"></span></td></tr>
            <tr><td><label >*Password:</label></td><td class="box1"  ><input id="password" name="password" type = "password" required/><span id="errorpassword"></span></td></tr>
            <tr><td><input  type="submit" value="Submit"> </td></tr>
        </tbody>
        </form>
    </table>
	<?php 

     $fullUrl= "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

    if(strpos($fullUrl,"signup=empty")==true){
    echo "<p style='color:Tomato;'> You did not fill in all fields!</p>";
    exit();
}
elseif (strpos($fullUrl,"signup=password")==true){
    echo "<p style='color:Tomato;' > Invalid User Name or password</p>";
    exit();

}


 ?>

									
						
						</section>
						</div>

		
