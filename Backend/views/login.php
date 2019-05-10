<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sboard - Login</title>
    <?php include_once('head.php') ?>

</head>
<body>

<?php include_once('navigation.php') ?>


<div class="content">
   <div class="contentBlock">
        <div class="contentBlockTitle">Login</div>
        <div>
            <form action="/session/login" method="post">
                <div class="inputs">
                Email<br> <input class="contentInput" type="text" name="Email" value=""><br>
                Wachtwoord<br> <input class="contentInput" type="password" name="Password" value="">
                </div>
                <input class="button" type="submit" value="Login">
            </form>
        </div>
    </div>

</div>


</body>
</html>