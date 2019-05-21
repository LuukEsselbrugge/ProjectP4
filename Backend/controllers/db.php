<?php

define("DBU","root");
define("DBP","");
define("DBN","librarysearch");


class Database
{
    public function __construct(){

    }

    public function connect(){
        try {
            $con = new PDO('mysql:host=localhost;dbname='.DBN.';charset=utf8mb4', DBU, DBP);
        } catch (PDOException $e) {
            echo 'Something went wrong: ' . $e->getMessage();
            die();
        }
        return $con;
    }


}

?>
