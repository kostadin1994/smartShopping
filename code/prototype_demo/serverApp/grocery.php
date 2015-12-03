<?php
$hostname_localhost ="localhost";
$database_localhost ="uniproject";
$username_localhost ="root";
$password_localhost ="";


$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);

// create the sql statement based on the ID for the shop that the user is interested in 
$password = $_POST['ID'];
$SQLCommand = "SELECT * FROM grocerylist".mysql_real_escape_string($password);
$result=mysql_query($SQLCommand);
$resArray = array();
$index = 0; 
// populate the array with the values from the query 
while ($row = mysql_fetch_assoc($result))
{ 
	$resArray[$index] = $row;
	$index++;
}
// encode in json format 
echo json_encode($resArray);
?>