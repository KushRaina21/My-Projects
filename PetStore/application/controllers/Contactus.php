<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Contactus extends CI_Controller {

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
		$this->load->view('header1');
		$this->load->view('contactus');
		$this->load->view('footer1');
	}

	public function submit()
	{


    	$this->load->library('session');
    $this->load->helper('form');
    $this->load->library('form_validation');

    $this->form_validation->set_rules('fname', 'First Name', 'required');
    


    if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header1');
		$this->load->view('contactus');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
  

  $this->form_validation->set_rules('lname', 'Last name', 'required');

  if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header1');
		$this->load->view('contactus');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
    $this->form_validation->set_rules('email', 'E-mail', 'required|valid_email');
     if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header1');
		$this->load->view('contactus');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
    $this->form_validation->set_rules('phone', 'phone', 'regex_match[/^([2-9][0-9]{2})[-]([0-9]{3})[-]([0-9]{4})$/]');
     if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header1');
		$this->load->view('contactus');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 
    $this->form_validation->set_rules('comments', 'comments', 'required');
     if ($this->form_validation->run() == FALSE) {
    	$this->load->helper('url');
      	$this->load->view('header1');
		$this->load->view('contactus');
		$this->load->view('footer1');
		$this->output->_display();
		 exit();
    } 

    else {

      $Contactusarray=array(
                        'fname'=>$this->input->post('fname'),
                        'lname'=>$this->input->post('lname'),
                        'email'=>$this->input->post('email'),
                        'phone'=>$this->input->post('phone'),
                        'comments'=>$this->input->post('comments'),
                        );

      $this->load->model('Contactus_model');
      $this->Contactus_model->insert_order($Contactusarray);
      $this->session->set_flashdata('success', "Thanks for Submitting we will contact you soon");
   	 $this->load->helper('form');
   	 $this->load->library('form_validation');
   	 $this->load->helper('url');
     $this->load->view('header1');
	$this->load->view('contactus');
		$this->load->view('footer1');
		$this->output->_display();
		$this->session->unset_userdata('success');
		 exit();
    }
}
}
