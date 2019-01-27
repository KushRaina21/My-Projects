<?php
  class Contactus_model extends CI_Model {
    public function __construct() {
      $this->load->database();
    }

  

    public function insert_order($Contactusarray) {
      $this->db->insert('contact', $Contactusarray);
       $return;
    }

   

      }
    

  

 ?>
