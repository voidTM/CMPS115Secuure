<?php
    //Setup connection variables
    $hostname = "localhost";
    $username = "root";
    $password = "";
    $dbname = secuure;
    $usertable= data;
 
    //Connect to the database
    $connection = mysql_connect($hostname, $username, $password);
    mysql_select_db($dbname, $connection);
 
    //Setup our query
    $query = "SELECT * FROM $usertable";
 
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
            echo $acc . "   " . $ws . "   " . $pwd . "\r\n";
        }
    }
?>