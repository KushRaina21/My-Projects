<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Client_model extends CI_Model {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html


	 */
	public function __construct()
	{
		$this->load->database();
	}


	public function insert_usertable($useridarray)
	{


		 



		$this->db->insert('user',$useridarray);

		
		$return;

		  



	}
public function get_uid($email)
	{

		$this->db->select('userid');
$this->db->where('email', $email);
//$this->db->from('user');


$query = $this->db->get('user');



/*foreach ($query->result() as $row)
{
        echo $row->userid;
}*/
return $query;

}

public function insert_clientDetails($clientarray)
	{

$this->db->insert('client',$clientarray);

		
		$return;


	}
		

		public function check_emailexists($email)
	{

$this->db->select('email');
$this->db->where('email', $email);
//$this->db->from('user');


$query = $this->db->get('user');




//$this->db->insert('client',$clientarray);


		
		return $query;


	}
		


		
}
