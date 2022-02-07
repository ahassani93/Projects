<?php  
require_once __DIR__ . '/formProc.php';
?>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Team Registration</title>
  </head>
  <body style="background-image: url('background.jpg'); background-repeat:no-repeat; background-size:cover;">
  <div class="container">
    <form action="form.php" method="post" >
	<div class="col">
	<h1 style="margin-left: 14px">Registration Form</h1>
  <br>
	</div>
	<ul>
	<li><a style="margin-left: 14px" href="displayRecords.php">Registered Members</a></li>
	</ul>
	<div class="col">
	<p style="margin-left: 14px"><?php echo $msg;?></p>
	</div>
  <div class="col">
  <div class="col">
    <label for="theName" style="margin-top: 15px">Name (letters only)<?php echo $nameError;?></label>
    <input type="text" style="background-color: #ADD8E6;" name="name" class="form-control" id="theName" value="<?php echo $nameValue;?>">
  </div>
  <div class="col">
	<label for="theAddress" style="margin-top: 15px">Address (only number and street)<?php echo $addressError;?></label>
    <input type="text" style="background-color: #ADD8E6;" name="address" class="form-control" id="theAddress" value="<?php echo $addressValue;?>">
  </div>
  <div class="col">
	<label for="thePhone" style="margin-top: 15px">Phone<?php echo $phoneError;?></label>
    <input type="text" style="background-color: #ADD8E6;" name="phone" class="form-control" id="thePhone" value="<?php echo $phoneValue;?>">
  </div>
  <div class="col">
	<label for="theEmail" style="margin-top: 15px">Email address<?php echo $emailError;?></label>
    <input type="text" style="background-color: #ADD8E6;" name="email" class="form-control" id="theEmail" value="<?php echo $emailValue;?>">
  </div>
  <div class="col">
	<label for="theDOB" style="margin-top: 15px">Date of birth (mm/dd/yyyy)<?php echo $dobError;?></label>
    <input type="text" style="background-color: #ADD8E6;" name="DOB" class="form-control" id="theDOB" value="<?php echo $dobValue;?>">
  </div>
  <div class="col">
	<button type="submit" name="submitForm" style="margin-top: 15px" class="btn btn-primary">Submit</button>
  </div>
  </div>
</form>
</div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>