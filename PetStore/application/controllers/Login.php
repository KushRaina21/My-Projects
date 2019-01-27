<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends CI_Controller {

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
		  $this->load->library('session');
		  $this->load->helper('form');
   	 $this->load->library('form_validation');
		$this->load->helper('url');
		$this->load->view('header');
		$this->load->view('login');
		$this->load->view('footer1');
	}

	public function login_form()
	{

	$this->load->library('session');
    $this->load->helper('form');
    $this->load->library('form_validation');

    $this->form_validation->set_rules('email', 'Email', 'required');
    


    if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header');
		$this->load->view('login');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
  

  $this->form_validation->set_rules('password', 'Password', 'required');

  if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header');
		$this->load->view('login');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
  

    else {

      $loginarray=array(
                        'email'=>$this->input->post('email'),
                        'password'=>$this->input->post('password')
                    
                        );

      $this->load->model('Login_model');
     $row= $this->Login_model->login($loginarray);
  if(isset($row))
       {
          if($row->roleid == "1" )
       
{
 $this->load->helper('form');
   	 $this->load->library('form_validation');
   	 $this->load->helper('url');
	$this->load->view('businesslogin');
	$this->output->_display();
		 exit();
	
}


elseif($row->roleid == "2" )
{
$this->load->helper('form');
   	 $this->load->library('form_validation');
   	 $this->load->helper('url');
	$this->load->view('clientLogin');
	$this->output->_display();
		 exit();
		
}

    }
    elseif(!isset($row))
{

$this->session->set_flashdata('error', "Username or Password is incorrect");
	$this->load->helper('url');
      	$this->load->view('header');
		$this->load->view('login');
		$this->load->view('footer1');
		$this->output->_display();
		$this->session->sess_destroy();
		 exit();
}
   
   	
    }
}
}

