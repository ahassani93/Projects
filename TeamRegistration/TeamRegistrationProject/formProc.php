<?php
require_once __DIR__ . '/src/PdoUtils.php';
require_once __DIR__ . '/Validation.php';

$nameError = "";
$nameValue = "";
$addressError = "";
$addressValue = "";
$phoneError = "";
$phoneValue = "";
$emailError = "";
$emailValue = "";
$dobError = "";
$dobValue = "";
$msg = "";

if(isset($_POST['submitForm']))
{
	$nameValue = $_POST["name"];
	$addressValue = $_POST["address"];
	$phoneValue = $_POST["phone"];
	$emailValue = $_POST["email"];
	$dobValue = $_POST["DOB"];
	$validationObject = new Validation();
	$pdo = new PdoUtils();
	$nameValidation = $validationObject->validateName($nameValue);
	$addressValidation = $validationObject->validateAddress($addressValue);
	$phoneValidation = $validationObject->validatePhone($phoneValue);
	$emailValidation = $validationObject->validateEmail($emailValue);
	$dobValidation = $validationObject->validateDOB($dobValue);
	
	if($nameValidation == "error" || $nameValue == "")
	{
		$nameError = "<span style='color: red; margin-left: 15px;'>Name cannot be blank and must be a standard name</span>";
	}
	else
	{
		$nameError = "";
	}
	
	if($addressValidation == "error" || $addressValue == "")
	{
		$addressError = "<span style='color: red; margin-left: 15px;'>Address cannot be blank and must be a valid address</span>";
	}
	else
	{
		$addressError = "";
	}
	
	if($phoneValidation == "error" || $phoneValue == "")
	{
		$phoneError = "<span style='color: red; margin-left: 15px;'>Phone cannot be blank and must be written as 999.999.9999</span>";
	}
	else
	{
		$phoneError = "";
	}
	
	if($emailValidation == "error" || $emailValue == "")
	{
		$emailError = "<span style='color: red; margin-left: 15px;'>Email cannot be blank and must be written as a proper email</span>";
	}
	else
	{
		$emailError = "";
	}
	
	if($dobValidation == "error" || $dobValue == "")
	{
		$dobError = "<span style='color: red; margin-left: 15px;'>Dob cannot be blank and must be written as 99/99/9999</span>";
	}
	else
	{
		$dobError = "";
	}
	
	if($nameError == "" && $addressError == "" && $phoneError == "" && $emailError == "" && $dobError == "")
	{
		$sql = "INSERT INTO info (name, address, phone, email, dob) VALUES (:nameValue, :addressValue, :phoneValue, :emailValue, :dobValue)";
		$bindings = [
		[':nameValue',$nameValue,'str'],
		[':addressValue',$addressValue,'str'],
		[':phoneValue',$phoneValue,'str'],
		[':emailValue',$emailValue,'str'],
		[':dobValue',$dobValue,'str']
		];
		$insertion = $pdo->boundInsertUpdateDelete($sql, $bindings);
	
		if($insertion == 'error')
		{
			$msg = "There was an error adding the record";
		}
		else
		{
			$msg = "Record has been added";
			$nameValue = "";	
			$addressValue = "";
			$phoneValue = "";
			$emailValue = "";
			$dobValue = "";
		}
	}
}
?>