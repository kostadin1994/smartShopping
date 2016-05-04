Contents of the project 
	srs- this folder stores all the neccessary .java files 
		classes- defines the structure of the objects used in the logic of the program 
		com.example.adapters - the files in this folder are responsible for creating the views in the project. 
		com.example.smartShopping - holds the activities (main classes which will be launched during runtime) 
		com.example.utils - files which provide some of the functionalities in the system 
			dbHelper.java - holds the methods used to store the bought items in the sqLite database 
			GPSTracker.java - used to provide the gps functionalities of the system. Please, note that this file has been downloaded from the Internet. for more information, please go to http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/
  			HelperHttp.java - holds the methods used to establish the communication between the client and the server 
	res - this folder stores the graphics used in the app 
		layout - stores .xml files which define the design of the GUI of the app 
		drawable - defines additional graphics used in the app 
			divider.xml - defines the divider used between the different members of the lists shown to the user 
			selector.xml - defines the behaviour of the items in the list when selected 

Contents of the repository 
	code/branches - holds all the neccessary files in order to run the project 
		androidApp - holds the files needed to install the android application 
		dbScripts - scripts which have to be imported in order to install the database 
		libs - folder which holds all the external libraries used in the project 
		serverApp- holds the .php scripts used to run the server 





Installation 
The following guide is written for Windows
make sure that you have installed the newest Android SDK 
make sure that you have installed the Android plugin for Eclipse 
1) Import the Android project in Eclipse 
	Checkout the repository 
	Navigate to code/branches 
	Copy and paste the androidApp folder 
	Open eclipse 
	File/Import 
	Select Existing Projects into Workplace and click next
	Click browse to select the androidApp folder 
	Name the imported project "smartShopping"  in order to follow the structure listed in the previous chapter 
	click Finish 
2) Install xampp 
 	Download the latest installer file from https://www.apachefriends.org/download.html#641
	Follow the onscreen instructions 
3) Install the server 
	go to the repository folder 
	copy the files located in code/branches/serverApp 
	go to the directory where xampp is installed 
	go to htdocs directory and create a folder called uniproject 
	paste the files in this folder 
4) install the database 
	go to the xampp folder and open xampp-control 
	start apache 
	open command prompt 
	type ipconfig in order to find your IP 
	copy and paste your IP address in the browser 
	when you see the xampp homepage navigate to phpmyadmin 
	create a database called uni project 
	navigate to the database 
	click import and import the files located in code/branches/dbScripts 
5) configure the Android app 
	go to src and open Grocery.java , MainActivity.java , nearbyShops.java , Payment.java, recharge.java and shopList.java
	in the lines String json=HelperHttp.getJSONResponseFromURL change the IP address with your IP 
	for example:  String json=HelperHttp.getJSONResponseFromURL("http://192.168.0.100:1234/uniproject/shopList.php", ht); should be changed to 
	 String json=HelperHttp.getJSONResponseFromURL("http://your_ip_address/uniproject/shopList.php", ht);
6) run the Android app 
	make sure that you have enabled usb debugging on your device 
	right click the Android project and click run as - android application 
	the default username and password for the app are : test123,test123

	
	
	