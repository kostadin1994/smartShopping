<?php
$hostname_localhost ="localhost";
$database_localhost ="uniproject";
$username_localhost ="root";
$password_localhost ="";


$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);

//reduces the balance of the given user 
$username = $_POST['username'];
$price = $_POST['price'];
$int = (int)$price;

$sql = "UPDATE users SET balance=balance-'".$int."' WHERE username='".$username."'";
$query_exec = mysql_query($sql) or die(mysql_error());
echo "success"; 
?>