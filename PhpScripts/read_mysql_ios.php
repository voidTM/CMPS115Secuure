<?php
    //Setup connection variables and user arguments
    $hostname = "98.234.141.183";
    $username = $_POST['arg_usr'];
    $password = $_POST['arg_pwd'];
    $dbname = secuure;
    $usertable= data;
    $read_acc = $_POST['arg_read_acc'];
    $read_ws = $_POST['arg_read_ws'];
    $uid = 0;
    
    // Fetch user id
    //Connect to the database
    $connection = mysql_connect($hostname, $username, $password);
    mysql_select_db($dbname, $connection);
    
    $query = "SELECT id FROM users WHERE username='$username'";
    
    //Run the Query
    $result = mysql_query($query);
    
    //Find the id for a particular username
    if($result)
    {
        while($row = mysql_fetch_array($result))
        {
            $uid = $row[id];
        }
        
    }

    
    //Connect to the database
    $connection = mysql_connect($hostname, $username, $password);
    mysql_select_db($dbname, $connection);
 
    //Setup our query
    $query = "SELECT * FROM $usertable WHERE userid=$uid AND account='$read_acc' AND website='$read_ws'";
 
    //Run the Query
    $result = mysql_query($query);

    //If the query returned results, loop through each result
    if($result)
    {
        while($row = mysql_fetch_array($result))
        {
            $acc = $row[account];
            $ws = $row[website];
            $pwd = $row[password];
            $note = $row[notes];
            echo $acc . "|" . $ws . "|" . $pwd . "|" . $note;
        }
    }
?>
