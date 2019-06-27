<!DOCTYPE html>
<html lang="en">
<head>
    <title>LibrarySearch - Instellingen</title>
    <?php include_once('head.php') ?>

</head>
<body>

<?php include_once('navigation.php') ?>


<div class="topMenuSecond">
    <div class="topMenuInner">
        <div class="topMenuTitle">Instellingen / <b id="fullname"><?=$user["Firstname"]?> <?=$user["Lastname"]?></b></div>
    </div>
</div>

<div class="content">
    <div class="usercover"> <div style="<?php if($user["Image"]!=""): ?>  background-image: url('/images/<?=$user["Image"]?>'); font-size: 0px;  <?php endif; ?>" onclick="document.getElementById('file').click()" class="UserImageLarge" id="namecode"><?=$user["NameCode"]?></div>
        <div style="<?php if($user["Image"]==""): ?> display: none; <?php endif; ?>"class="UserImageHover"><i onclick="confirmbox('Wil je de huidige profiel foto verwijderen?','/users/deleteimage?UserID=<?=$user["UserID"]?>',event); event.stopPropagation();" class="mdi mdi-close-circle"></i></div>
    </div>
    <input accept="image/x-png,image/gif,image/jpeg" style="display: none;" id="file" type="file" data-id="<?=$user["UserID"]?>" onchange="uploadProfileImage(this)"/>
    <?php include_once('alert.php') ?>
    <div class="contentBlock">

        <form id="form">
            <div class="inputs">
                <div class="description">Naam</div> <input required onkeyup="userChanged(this)" class="contentInput" type="text" value="<?=$user["Firstname"]?>">
                <div class="description">Achternaam</div> <input onkeyup="userChanged(this)" class="contentInput" type="text" value="<?=$user["Lastname"]?>">
                <div class="description">Email</div> <input required onkeyup="userChanged(this)" class="contentInput" type="email" value="<?=$user["Email"]?>">

                <div class="description">Nieuw wachtwoord</div> <input onkeyup="userChanged(this)" class="contentInput" type="password" value="">
                <div class="description">Nieuw wachtwoord herhalen</div> <input onkeyup="userChanged(this)" class="contentInput" type="password" value="">
        </form>
                <button type="button" data-userid="<?=$user["UserID"]?>" disabled onclick="userSave(this)" class="button buttonSave" ><i class="mdi mdi-check"></i> Opgeslagen</button>
            </div>
    </div>
</div>

</body>
</html>
