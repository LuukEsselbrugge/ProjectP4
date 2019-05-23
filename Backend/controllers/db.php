<?php

define("DBU","loves21_sboard");
define("DBP","RQ6J2hzs40");
define("DBN","loves21_sboard");


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
