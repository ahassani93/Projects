<?php
require_once __DIR__ . '/src/PdoUtils.php';

$output = "";
$pdo = new PdoUtils();
$sql = "SELECT * FROM info";
$records = $pdo->unboundQuery($sql);

if($records == 'error')
{
	$output = "There was an error returning records from database";
}
else 
{
	if(count($records) != 0)
	{
		$output = "<table style='margin-top: 20px; background-color: #ADD8E6;' class='table table-bordered table-striped'><thead><tr>";
		$output .= "<th>Name</th><th>Address</th><th>Phone</th><th>Email</th><th>DOB</th><tbody>";
		
		foreach ($records as $row)
		{
			$rowName = $row['name'];
			$rowAddress = $row['address'];
			$rowPhone = $row['phone'];
			$rowEmail = $row['email'];
			$rowDob = $row['dob'];
			$output .= "<tr><td>$rowName</td>";
			$output .= "<td>$rowAddress</td>";
			$output .= "<td>$rowPhone</td>";
			$output .= "<td>$rowEmail</td>";
			$output .= "<td>$rowDob</td></tr>";
		}
		
		$output .= "</tbody></table>";
	}
	else 
	{
		$output = "No records found";
	}
}
?>