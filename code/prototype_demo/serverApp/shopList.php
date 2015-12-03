<?php
$hostname_localhost ="localhost";
$database_localhost ="uniproject";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);
$SQLCommand = "SELECT * FROM shops ";
$result=mysql_query($SQLCommand);
$resArray = array();
$index = 0; 
while ($row = mysql_fetch_assoc($result))
{ 
	$resArray[$index] = $row;
	$index++;
}
echo json_encode($resArray);
?>