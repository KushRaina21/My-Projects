<?php
  class Login_model extends CI_Model {
    public function __construct() {
      $this->load->database();
    }

  

    public function login($loginarray) {
    	$email=$loginarray['email'];
    	$password=$loginarray['password'];
       $query = $this->db->query("SELECT * FROM user  WHERE email = '$email'  AND password= '$password';");
       $row = $query->row();
       return $row;
     
}

   

      }
    

  

 ?>