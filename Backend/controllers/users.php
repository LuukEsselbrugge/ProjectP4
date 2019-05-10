<?php

class usersController
{
    private $db;

    public function __construct() {
        $this->db = (new Database())->connect();
            if (isset($_GET["uri"]) && isset($_GET["uri2"])) {
                $sql = $this->db->prepare("SELECT * FROM Users WHERE UserID=?");
                $status = $sql->execute(array($_GET["uri2"]));
                if (!$status) {
                    echo "Something went wrong. Please try again later";
                    die();
                }

                if($_GET["uri2"] != "update" && $_GET["uri2"] != "updateImage" && $_GET["uri2"] != "add" && $_GET["uri2"] != "delete" && $_GET["uri2"] != "deleteimage") {
                    $user = $sql->fetchAll(PDO::FETCH_ASSOC)[0];
                    if(isset($user["Lastname"][0])) {
                        $user["NameCode"] = strtoupper($user["Firstname"][0] . $user["Lastname"][0]);
                    }else{
                        $user["NameCode"] = strtoupper($user["Firstname"][0]);
                    }
                    require_once './views/user.php';
                }

            } else {
                if ( $_SESSION["UserData"]["Admin"] == "1") {
                    $sql = $this->db->prepare("SELECT * FROM Users ORDER BY Firstname");
                    $status = $sql->execute(array());
                    if (!$status) {
                        echo "Something went wrong. Please try again later";
                        die();
                    }
                    $users = $sql->fetchAll(PDO::FETCH_ASSOC);
                    foreach ($users as $key => $item) {
                        if (isset($item["Lastname"][0])) {
                            $users[$key]["NameCode"] = strtoupper($item["Firstname"][0] . $item["Lastname"][0]);
                        } else {
                            $users[$key]["NameCode"] = strtoupper($item["Firstname"][0]);
                        }
                    }
                    require_once './views/users.php';
                }
            }
        }

    public function add(){
        if($_SESSION["UserData"]["Admin"] == "1") {
            $id = substr(str_shuffle(md5(time())), 0, 8);
            $sql = $this->db->prepare("INSERT INTO Users (UserID,Firstname,Lastname,Email,Password,Image) VALUES (?,?,?,?,?,?)");
            $status = $sql->execute(array($id, "Gebruiker", "", "", "", ""));
            if (!$status) {
                echo "Something went wrong. Please try again later";
                var_dump($sql->errorInfo());
                die();
            }
            for ($x = 1; $x <= 5; $x++) {
                $sql = $this->db->prepare("INSERT INTO Availability (UserID,DayOfWeek,StartTime,EndTime) VALUES (?,?,?,?)");
                $status = $sql->execute(array($id, $x, "08:00:00", "17:00:00"));
                if (!$status) {
                    echo "Something went wrong. Please try again later";
                }
            }

            header('Content-Type: application/json');
            echo json_encode($id);
        }
    }

    public function update(){
        if($_SESSION["UserData"]["UserID"] == $_GET["UserID"] ||$_SESSION["UserData"]["Admin"] == "1") {

            if($_SESSION["UserData"]["Admin"] == "0"){
                $sql = $this->db->prepare("UPDATE Users SET Email=?, Firstname=?, Lastname=? WHERE UserID=?");
                $status = $sql->execute(array($_GET["Email"], $_GET["Firstname"], $_GET["Lastname"], $_GET["UserID"]));
            }else{
                $sql = $this->db->prepare("UPDATE Users SET Email=?, Firstname=?, Lastname=?, Admin=?, Visible=?, AMode=?, Publish=? WHERE UserID=?");
                $status = $sql->execute(array($_GET["Email"], $_GET["Firstname"], $_GET["Lastname"], $_GET["Admin"], $_GET["Visible"], $_GET["AMode"], $_GET["Publish"], $_GET["UserID"]));
            }
            header('Content-Type: application/json');

            if (!$status) {
                echo "Something went wrong. Please try again later";
                die();
            }

            if (isset($_GET["Password"]) && $_GET["Password"] != "") {
                $sql = $this->db->prepare("UPDATE Users SET Password=? WHERE UserID=?");
                $status = $sql->execute(array(hash('sha256', ($_GET["UserID"] . $_GET["Password"])), $_GET["UserID"]));
                if (!$status) {
                    echo "Something went wrong. Please try again later";
                    die();
                }
            }

            if($_SESSION["UserData"]["UserID"] == $_GET["UserID"]) {
                $sql = $this->db->prepare("SELECT * FROM Users WHERE UserID = ?");
                $status = $sql->execute(array($_GET["UserID"]));
                if (!$status) {
                    echo "Something went wrong. Please try again later";
                    die();
                }
                $data = $sql->fetch(PDO::FETCH_ASSOC);
                $_SESSION["UserData"] = $data;
                $_SESSION["UserData"]["NameCode"] = strtoupper($data["Firstname"][0] . $data["Lastname"][0]);
            }

            echo json_encode(true);
        }
    }

    public function updateImage(){
        header('Content-Type: application/json');
        if(isset($_FILES["photo"]) && isset($_GET["UserID"])) {
            if ($_SESSION["UserData"]["UserID"] == $_GET["UserID"] || $_SESSION["UserData"]["Admin"] == "1") {

                $sql = $this->db->prepare("SELECT * FROM Users WHERE UserID=?");
                $status = $sql->execute(array($_GET["UserID"]));
                if (!$status) {
                    echo "Something went wrong. Please try again later";
                    die();
                }
                $user = $sql->fetchAll(PDO::FETCH_ASSOC)[0];

                $id = substr(str_shuffle(md5(time())), 0, 32);
                $sql = $this->db->prepare("UPDATE Users SET Image=? WHERE UserID=?");
                $status = $sql->execute(array($id . ".png", $_GET["UserID"]));
                if (!$status) {
                    echo "Something went wrong. Please try again later";
                }

                $file_type = $_FILES['photo']['type'];
                $allowed = array("image/jpeg", "image/gif", "image/png", "image/jpg");
                if (in_array($file_type, $allowed)) {
                    move_uploaded_file($_FILES["photo"]["tmp_name"], $_SERVER['DOCUMENT_ROOT'] . "/images/" . $id . ".png");
                    if($user["Image"] != "") {
                        unlink($_SERVER['DOCUMENT_ROOT'] . "/images/" . $user["Image"]);
                    }
                    if ($_SESSION["UserData"]["UserID"] == $_GET["UserID"]) {
                        $_SESSION["UserData"]["Image"] = $id . ".png";
                    }
                    echo json_encode(array(true, "/images/" . $id . ".png"));
                }
            }
        }
    }

    public function deleteimage(){
        if(isset($_GET["UserID"])) {
            $sql = $this->db->prepare("SELECT * FROM Users WHERE UserID=?");
            $status = $sql->execute(array($_GET["UserID"]));
            if (!$status) {
                echo "Something went wrong. Please try again later";
                die();
            }
            $user = $sql->fetchAll(PDO::FETCH_ASSOC)[0];

            if ($_SESSION["UserData"]["UserID"] == $_GET["UserID"] || $_SESSION["UserData"]["Admin"] == "1") {
                $sql = $this->db->prepare("UPDATE Users SET Image=? WHERE UserID=?");
                $sql->execute(array("",$_GET["UserID"]));
                if($user["Image"] != "") {
                    unlink($_SERVER['DOCUMENT_ROOT'] . "/images/" . $user["Image"]);
                }
            }
        }
        header("Location: /users/".$_GET["UserID"]);
    }

    public function delete(){
        if(isset($_GET["id"])) {
            if ($_SESSION["UserData"]["Admin"] == "1") {
                $id = stripslashes($_GET["id"]);
                $sql = $this->db->prepare("DELETE FROM Users WHERE UserID = ?");
                $sql->execute(array($id));
                $sql = $this->db->prepare("DELETE FROM Sessions WHERE UserID = ?");
                $sql->execute(array($id));
            }
        }
        header("Location: /users");
    }



}

?>