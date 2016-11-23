<?php
    // Setup connection variables and user arguments
    $hostname = "98.234.141.183";
    $username = "cs115";
    $password = "insecuurity";
    $dbname = secuure;
    $usertable = users;
    $datatable = data;

    // Create connection
    $conn = mysqli_connect($hostname, $username, $password);
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error() . "\r\n");
    }

    //Create database
    $sql = "CREATE DATABASE $dbname";
    if (mysqli_query($conn, $sql)) {
        echo "Database created successfully" . "\r\n";
    } else {
        echo "Error creating database: " . mysqli_error($conn) . "\r\n";
    }

    mysqli_close($conn);


    // Creates user table
    
    // Create connection to database
    $conn = mysqli_connect($servername, $username, $password, $dbname);
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error() . "\r\n");
    }

    //Create user table
    $sql = "CREATE TABLE $usertable ( `id` INT NOT NULL AUTO_INCREMENT, `username` VARCHAR(50) NOT NULL, `first_name` VARCHAR(50) NOT NULL, `last_name` VARCHAR(50) NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB";

    if (mysqli_query($conn, $sql)) {
        echo "Table created successfully" . "\r\n";
    } else {
        echo "Error creating table: " . mysqli_error($conn) . "\r\n";
    }

    mysqli_close($conn);

    
    // Ensure unique usernames
    
    // Create connection
    $conn = mysqli_connect($servername, $username, $password, $dbname);
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error() . "\r\n");
    }
    
    // Alter user talbe to have constraint
    $sql = "ALTER TABLE $usertable ADD CONSTRAINT uniqueness UNIQUE (username)";
    
    if (mysqli_query($conn, $sql)) {
    } else {
        echo "Error creating table: " . mysqli_error($conn) . "\r\n";
    }
    
    mysqli_close($conn);
    
    // Create connection
    $conn = mysqli_connect($servername, $username, $password, $dbname);
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error() . "\r\n");
    }
    
    // Create data table
    $sql = "CREATE TABLE $datatable ( `userid` INT NOT NULL REFERENCES $usertable(`id`) , `account` VARCHAR(50) NOT NULL , `website` VARCHAR(50) NOT NULL, `password` VARCHAR(50) NOT NULL, `notes` VARCHAR(500) NOT NULL ) ENGINE = InnoDB";
    
    if (mysqli_query($conn, $sql)) {
        echo "Table created successfully" . "\r\n";
    } else {
        echo "Error creating table: " . mysqli_error($conn) . "\r\n";
    }
    
    mysqli_close($conn);
?>
