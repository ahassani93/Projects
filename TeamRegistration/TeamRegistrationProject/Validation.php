<?php
class Validation
{
	private $error = false;
	
	public function validateName($value)
	{
		$match = preg_match('/^[a-z-\']( ?[a-z-\']){0,49}$/i', $value);
	
		if(!$match)
		{
			$this->error = true;
			return "error";
		}
		else
		{
			return "";
		}
	}

	public function validateAddress($value)
	{
		$match = preg_match('/^[0-9]{1,10}( ?[a-z-\.]){0,40}$/i', $value);
	
		if(!$match)
		{
			$this->error = true;
			return "error";
		}
		else
		{
			return "";
		}
	}

	public function validatePhone($value)
	{
		$match = preg_match('/^[0-9]{3}\.[0-9]{3}\.[0-9]{4}$/', $value);
	
		if(!$match)
		{
			$this->error = true;
			return "error";
		}
		else
		{
			return "";
		}
	}

	public function validateEmail($value)
	{
		$match = preg_match('/^([a-z0-9-\_]\.?){1,30}@[a-z0-9-\_]{1,20}\.[a-z]{3,4}$/', $value);
	
		if(!$match)
		{
			$this->error = true;
			return "error";
		}
		else
		{
			return "";
		}
	}

	public function validateDOB($value)
	{
		$match = preg_match('/^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/', $value);
	
		if(!$match)
		{
			$this->error = true;
			return "error";
		}
		else
		{
			return "";
		}
	}

	public function checkErrors()
	{
		return $this->error;
	}
}
?>