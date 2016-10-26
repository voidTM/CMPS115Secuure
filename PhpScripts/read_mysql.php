<?php
    //Setup connection variables
    $hostname = "localhost";
    $username = $_POST['arg_usr'];
    $password = $_POST['arg_pwd'];
    $dbname = secuure;
    $usertable= data;
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
            //$records[] = $row;
            $uid = $row[id];
        }
        
    }

    
    //Connect to the database
    $connection = mysql_connect($hostname, $username, $password);
    mysql_select_db($dbname, $connection);
 
    //Setup our query
    $query = "SELECT * FROM $usertable WHERE userid=$uid";
 
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
            echo $acc . "  |  " . $ws . "  |  " . $pwd . " ----------- ";
        }
    }
?>
