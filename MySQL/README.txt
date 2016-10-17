README

1) Make sure user has MySQL database installed on their computer, if not go to http://dev.mysql.com/downloads/ and download the correct version.

2) Make sure user has PHP installed on their computer.  Scripts written with PHP v.5.5.36.

3) Once MySQL has been installed, change the password for root, or create a new user account with all privileges.

4) Create a database called “secuure”, and within it create a table with three columns named: “account”, “website”, and “password”.  Ensure each column is of type varchar and is of adequate length.

5) Update login information and desired values to input into the database within the scripts, insert_mysql.php and read_mysql.php.

6) Run scripts by typing “php <filename>.php” in a terminal window. 