<!DOCTYPE html>
<html>
<head><meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<link rel="stylesheet" type="text/css" href="/phproot/CodeIgniter-3.1.9/css/petstorecss.css">
<script>
function validateForm() {
    var fname = document.forms["myForm"]["fname"].value;
    var lname = document.forms["myForm"]["lname"].value;
    var email = document.forms["myForm"]["email"].value;
    var phone = document.forms["myForm"]["phone"].value;
    var comments = document.forms["myForm"]["comments"].value;
    
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
    else if (comments=="") {
document.getElementById('errorcomments').innerHTML="*Please enter comments";
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
 </head> 
	<body background="css/background.gif"> 
		<div>
			<h1>Pet Store</h1>
		</div>
		<div class="row">
			<div class="column left" > 
				<center>
					<nav class="navclass">
						<a href="<?php echo base_url(); ?>index.php/Home/load">Home</a> <br>
						<a href="<?php echo base_url(); ?>index.php/Aboutus">About Us</a> <br>
						<a href="<?php echo base_url(); ?>index.php/Contactus">Contact Us</a><br>
						<a href="<?php echo base_url(); ?>index.php/Client">Client</a><br>
						<a href="<?php echo base_url(); ?>index.php/Service">Service</a><br>
						<a href="<?php echo base_url(); ?>index.php/Login">Login</a>
					</nav>
				</center>
			</div>