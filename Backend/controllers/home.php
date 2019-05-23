<?php

class homeController
{
    private $db;

    public function __construct() {

        //$this->db = (new Database())->connect();
        if(!isset($_GET["uri2"])) {

            require_once './views/home.php';
        }
    }
}

?>