<?php
    //Setup connection variables
    $hostname = "localhost";
    $username = "root";
    $password = "";
    $dbname = secuure;
    $usertable = data;
    $acc = $argv[1];
    $ws = $argv[2];
    $pwd = $argv[3];

    //php insert_mysql.php <account> <website> <password>

    if($argc != 4){
        exit("Usage: php insert_mysql.php <account> <website> <password>" . "\r\n");
    }

    // Create connection
    $conn = mysqli_connect($hostname, $username, $password, $dbname);
   
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    $sql = "INSERT INTO `$usertable` (`account`, `website`, `password`) VALUES ('$acc', '$ws', '$pwd')";

    if (mysqli_query($conn, $sql)) {
        echo "New record created successfully" . "\r\n";
    } else {
        echo "Error: " . $sql . " " . mysqli_error($conn);
    }

    mysqli_close($conn);
?>