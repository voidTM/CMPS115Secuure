<?php
    //Setup connection variables and user arguments
    $hostname = "98.234.141.183";
    $username = $_POST['arg_usr'];
    $password = $_POST['arg_pwd'];
    $dbname = secuure;
    $usertable = data;
    $acc = $_POST['arg_add_acc'];
    $ws = $_POST['arg_add_ws'];
    $pwd = $_POST['arg_add_pwd'];
    $note = $_POST['arg_add_note'];
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


    // Insert data
    // Create connection
    $conn = mysqli_connect($hostname, $username, $password, $dbname);
   
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    // Inserts data into the data table
    $sql = "INSERT INTO `$usertable` (`userid`, `account`, `website`, `password`, `notes`) VALUES ('$uid', '$acc', '$ws',  '$pwd', '$note')";

    if (mysqli_query($conn, $sql)) {
        echo "New record created successfully" . "\r\n";
    } else {
        echo "Error: " . $sql . " " . mysqli_error($conn);
    }

    mysqli_close($conn);
 
?>
