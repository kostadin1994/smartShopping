<?php
$hostname_localhost ="localhost";
$database_localhost ="uniproject";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);
 
$username = $_POST['username'];
$password = $_POST['password'];
$query_search = "select * from users where username = '".$username."' AND pass = '".$password. "'";
$query_exec = mysql_query($query_search) or die(mysql_error());
$rows = mysql_num_rows($query_exec);
 if($rows == 0) { 
 
echo "fail"; 
 }
 else  {
   $SQLCommand = "select * from users where username = '".$username."' AND pass = '".$password. "'";
$result=mysql_query($SQLCommand);
$resArray= "";
while ($row = mysql_fetch_assoc($result)){
$resArray=$row;
{ 	
}

echo json_encode($resArray);

}

}
?>
