<?php
    // Setup connection variables and user arguments
    $hostname = "98.234.141.183";
    $username = "cs115";
    $password = "insecuurity";
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

    // Inserts user data into user table
    $sql = "INSERT INTO `$usertable` (`username`, `first_name`, `last_name`) VALUES ('$usr', '$firstname', '$lastname')";

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
    
    $sql = "CREATE USER '$usr'@'%' IDENTIFIED BY '$pwd'";
    
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
    
    // Grants users SELECT, INSERT, DELETE, and UPDATE privileges only on secuure database
    $sql = "GRANT SELECT, INSERT, DELETE, UPDATE ON $dbname.* TO '$usr'@'%'";
    
    if (mysqli_query($conn, $sql)) {
    } else {
        echo "Error: " . $sql . " " . mysqli_error($conn);
    }
    
    mysqli_close($conn);

?>
