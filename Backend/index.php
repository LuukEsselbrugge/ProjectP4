<?php
//error_reporting(E_ALL | E_STRICT);
error_reporting();
ini_set('display_errors', 0);


foreach(glob("controllers/*.php") as $file){
    require $file;
}
$ses = new SessionController;
$token = "";

if($ses->checkSession() || isset($_GET["uri"]) && $_GET["uri"] == "session" || isset($_GET["uri"]) && $_GET["uri"] == "upload"
    || isset($_GET["token"]) && $_GET["token"] == $token && isset($_GET["uri"]) && $_GET["uri"] == "board") {

    if (isset($_GET["uri"])) {
        $cont = $_GET["uri"] . "Controller";
        $cont = new $cont();
    } else {
        $cont = "homeController";
        $cont = new $cont();
    }

    if (isset($_GET["uri2"])) {
        if (method_exists($cont, $_GET["uri2"])) {
            $method = $_GET["uri2"];
            $cont->$method();
        }
    }

}else{
    $ses->showLogin();
}


?>