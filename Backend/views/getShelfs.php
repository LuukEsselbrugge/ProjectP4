<?php
function dbConnect(){
	$dbHost = "localhost";
    $dbName = "librarysearch";
	$dbUser = "root";
    $dbPass = "";
    
	if(!($conn=mysqli_connect($dbHost, $dbUser, $dbPass))){
		echo "Error on connect.";
	}else{
		if(!(mysqli_select_db($conn, $dbName))){
			echo mysqli_error();
			echo "error on database connection. Check your settings.";
		}else{
			return $conn;
		}
	}
}

function checkBookshelfs(){
    $conn = dbConnect();
    $table = "shelf";
    $getTable = $conn->query("SELECT MAX(bookshelf) AS Bookshelfs FROM ". $table);
    $conn->close();
    while($number = $getTable->fetch_assoc()){
        $bookshelfs = $number["Bookshelfs"];
    }
    return $bookshelfs;
}

function showShelf($shelfID){
    $conn = dbConnect();
    $table = "shelf";
    $getTable = $conn->query("SELECT * FROM ". $table . " WHERE bookshelf = " . $shelfID);
    $conn->close();
    return $getTable;
}
?>
