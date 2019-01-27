



			<div class="column right">
				<div>
					<!-- <img class="reshape" src="images/heroroad.jpg" alt="heroroad.jpg"/> -->
					<img class="reshape" src="/phproot/CodeIgniter-3.1.9/images/pet store banner 7 png.png">
				</div>
				<article>
					<div class="divstyle">
						<h2>Contact Us </h2>
						<p>Required information is marked with asterik (*).
						<section>
	<table>
		<!-- <form name="myForm"  method="post"   action="contactus_action.php" onsubmit="return validateForm()" novalidate > -->
			<form name="myForm"  method="post"   action="contactus_action.php" novalidate>

			
        <tbody cellpadding="20">

			<tr><td><label >*First Name:</label></td><td class="box1"  ><input id="fname" name="fname"  type="text" required /><span id="errorfname"></span></td></tr>
            <tr><td><label >*Last Name:</label></td><td class="box1"   ><input id="lname" name="lname" type = "text" required/><span id="errorlname"></span></td></tr>
            <tr><td><label >*E-mail:</label></td><td class="box1"   ><input id="email" name="email" type="email" required/><span id="erroremail"></span></td></tr>
            <tr><td><label >Phone:</label></td><td class="box1"   ><input id="phone" name="phone" type="tel" placeholder="123-456-7890"
           pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"/><span id="errorphone"></span></td></tr>

           <!--  <tr><td><label >*First Name:</label></td><td class="box1" ><input name="fname" type="text" required></td></tr>
            <tr><td><label >*Last Name:</label></td><td class="box1"  ><input name="lname"  type = "text" required></td></tr>
            
            <tr><td><label >*E-mail:</label></td><td class="box1"  ><input name="email"  type="email" required></td></tr>

            <tr><td><label >Phone:</label></td><td class="box1" ><input name="phone"  type="tel" placeholder="123-456-7890"
           pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required></td></tr> -->
            <tr><td><label >*Comments:</label></td><td class="box1"  ><textarea name="comments" type = "textarea" required></textarea> <span id="errorcomments"></span> </td></tr>
            <tr><td><input  type="submit" value="Submit"> </td></tr>
            
        </tbody>
   

        </form>
    </table>
	<?php 
     $fullUrl= "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

    if(strpos($fullUrl,"signup=empty")==true){
    echo "<p style='color:Tomato;'> You did not fill in all required  fields!</p>";
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
    echo "<p style='color:Tomato;'> You did not fill in the right email format!</p>";
    exit();
}
elseif (strpos($fullUrl,"signup=phone")==true){
    echo "<p style='color:Tomato;'> You did not fill in the right phone format!</p>";
    exit();
}
elseif (strpos($fullUrl,"signup=comments")==true){
    echo "<p style='color:Tomato;' > Comment size should be between 5 to 100 character</p>";
    exit();

}
elseif (strpos($fullUrl,"signup=success")==true){
    echo "<p style='color:Tomato;' >Thanks for visiting us!! We will contact you soon</p>";
    exit();
}
 ?>

									