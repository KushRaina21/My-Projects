


<!DOCTYPE html>
<html>
<head><meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="/phproot/CodeIgniter-3.1.9/css/petstorecss.css"></head> 
	<body background="css/background.gif"> 
		<div>
			<h1> Business's Pet Store</h1>
		</div>
		<div class="row">
			<div class="column left" > 
				<center>
					
				</center>
			</div>
			<div class="column right">
				<div>
					<!-- <img class="reshape" src="images/heroroad.jpg" alt="heroroad.jpg"/> -->
					<img class="reshape" src="/phproot/CodeIgniter-3.1.9/images/pet store banner 5 png (1).png">
				</div>
				<article>
					<div class="divstyle">
						<h2>My Business </h2>
						<p>Required information is marked with asterik (*).
						<section>
	<table class="tableclass">

		<form method="get"   action="businesslogin_action.php">
        <tbody cellpadding="20">
            <tr><td><label >Business Name</label></td><td class="box1" ><input name="name" type="text"></td></tr>
            <tr><td><label >*Service:</label></td><td class="box1"  ><input name="service" type = "text" required></td></tr>
            <tr><td><input  type="submit" value="Add New One"> </td></tr>
        </tbody>
         <!-- <input type="hidden" name="userId" value="<?php echo $_GET["userId"];?>" /> -->
        
        </form>
    </table>
	<a href "<?php echo base_url(); ?>index.php/Logout">Logout</a>

									
						
						</section>
						</div>
						<footer>
							<span >Copyright &copy; 2018 Pet Store</span>
							<br><span><a href="mailto:kush@raina.com">kush@raina.com</a></span> 
						</footer>
					</article>
					
				</div>
			</div>
		</body>
		</html>

		
<?php 
     $fullUrl= "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

    if(strpos($fullUrl,"signup=empty")==true){
    echo "<p style='color:Tomato;'> Please fill all mandatory fields!</p>";
    exit();
}

elseif (strpos($fullUrl,"signup=success")==true){
    echo "<p style='color:Tomato;' >Service record successfully added!!!</p>";
    exit();
}


 ?>