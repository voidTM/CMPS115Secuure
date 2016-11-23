<?php
    //Setup connection variables
    $hostname = "localhost";
    $username = $_POST['arg_usr'];
    $password = $_POST['arg_pwd'];
    $dbname = "secuure";
    $result = False;


    // Create connection
    $conn = mysqli_connect($hostname, $username, $password, $dbname, 3306);
   
    // Check connection
    if (!$conn) {
        //die("Connection failed: " . mysqli_connect_error());
        $result = False;
    }
    else {
        $result = True;
    }

    mysqli_close($conn);
    
    $resultarray = Array("login" => $result);
    header("Content-Type: application/json");
    echo json_encode($resultarray);
    
?>
