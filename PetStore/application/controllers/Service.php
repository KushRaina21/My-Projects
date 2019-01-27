<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Service extends CI_Controller {

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
	public function index()
	{

		$this->load->helper('form');
    $this->load->library('form_validation');
$this->load->library('session');
		$this->load->helper('url');
		$this->load->view('header');
		$this->load->view('service');
		$this->load->view('footer1');

	}


	public function submit()
	{


		$this->load->helper('form');
    $this->load->library('form_validation');

    $this->form_validation->set_rules('fname', 'First Name', 'required');
    
$this->load->library('session');

    if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header');
		$this->load->view('service');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
  

  $this->form_validation->set_rules('lname', 'Last Name', 'required');

  if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header');
		$this->load->view('service');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
    $this->form_validation->set_rules('email', 'E-mail', 'required|valid_email');
     if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header');
		$this->load->view('service');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
    $this->form_validation->set_rules('phone', 'phone', 'regex_match[/^([2-9][0-9]{2})[-]([0-9]{3})[-]([0-9]{4})$/]');
     if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header');
		$this->load->view('service');
		$this->load->view('footer1');
		$this->output->_display();

		 exit();
    } 
    

    else {
$this->load->library('session');
      $clientarray=array(
                        'fname'=>$this->input->post('fname'),
                        'lname'=>$this->input->post('lname'),
                        'email'=>$this->input->post('email'),
                        'phone'=>$this->input->post('phone'),
                        /*'businessname'=>$this->input->post('businessname'),*/
                        );

		$ran=$this->randomPassword();


 		$useridarray=array(
                        
                        'email'=>$this->input->post('email'),
                        
                        'password'=>$ran,
                        'roleid'=>1,
                        );
		$this->load->model('Service_model');

		 $emailexist=$this->Service_model->check_emailexists($useridarray['email']);
		 $emailidfound="";
		
 foreach ($emailexist->result() as $row)
		{
		       $emailidfound=$row->email;
		}

		//echo $emailidfound;
		if (!empty($emailidfound)) {



$this->session->set_flashdata('error', "Email Exist");
				 $this->load->helper('url');
		      	$this->load->view('header');
				$this->load->view('service');
				$this->load->view('footer1');
				$this->output->_display();
		$this->session->unset_userdata('error');
				exit();

			# code...
		} 

		else {

		$this->Service_model->insert_usertable($useridarray);
		$this->load->model('Service_model');

		      $uid=$this->Service_model->get_uid($useridarray['email']);

		      foreach ($uid->result() as $row)
		{
		        $clientarray['userid']=$row->userid;
		}


		      

		$this->Service_model->insert_clientDetails($clientarray);
$this->session->set_flashdata('success', "Service Details entered sucessfully");
		      $this->load->helper('url');
		      	$this->load->view('header');
				$this->load->view('service');
				$this->load->view('footer1');
				$this->output->_display();
				$this->session->unset_userdata('success');
				exit();
   	 }
    }






		}
		

function randomPassword() {
    $alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
    $pass = array(); //remember to declare $pass as an array
    $alphaLength = strlen($alphabet) - 1; //put the length -1 in cache
    for ($i = 0; $i < 8; $i++) {
        $n = rand(0, $alphaLength);
        $pass[] = $alphabet[$n];
    }
    return implode($pass); //turn the array into a string
}
		
}
