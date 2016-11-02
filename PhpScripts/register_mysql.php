<?php
    //Setup connection variables
    $hostname = "localhost";
    $username = "root";
    $password = "";
    $dbname = secuure;
    $usertable = users;
    $usr = $_POST['arg_usr'];
    $pwd = $_POST['arg_pwd'];
    $firstname = $_POST['arg_fname'];
    $lastname = $_POST['arg_lname'];

    // Create connection
    $conn = mysqli_connect($hostname, $username, $password, $dbname);
   
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    $sql = "INSERT INTO `$usertable` (`username`, `first_name`, `last_name`, `mpass`) VALUES ('$usr', '$firstname', '$lastname', '$pwd')";

    if (mysqli_query($conn, $sql)) {
        echo "New record created successfully" . "\r\n";
    } else {
        echo "Error: " . $sql . " " . mysqli_error($conn);
    }

    mysqli_close($conn);
    
    
    // Create new user account on database
    // Create connection
    $conn = mysqli_connect($hostname, $username, $password, $dbname);
    
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }
    
    $sql = "CREATE USER '$usr'@'localhost' IDENTIFIED BY '$pwd'";
    
    if (mysqli_query($conn, $sql)) {
        echo "New user registered successfully" . "\r\n";
    } else {
        echo "Error: " . $sql . " " . mysqli_error($conn);
    }
    
    mysqli_close($conn);
    
    
    // Grants privileges
    // Create connection
    $conn = mysqli_connect($hostname, $username, $password, $dbname);
    
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }
    
    $sql = "GRANT SELECT, INSERT, DELETE ON $dbname.* TO '$usr'@'localhost'";
    
    if (mysqli_query($conn, $sql)) {
    } else {
        echo "Error: " . $sql . " " . mysqli_error($conn);
    }
    
    mysqli_close($conn);

?>
