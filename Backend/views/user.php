<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sboard - Instellingen</title>
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

            <div style="<?php if($_SESSION["UserData"]["Admin"] == "0"): ?>display: none;<?php endif;?>" class="description">Aanwezigheid<br>
            <button type="button" onclick="buttonCheck(this)" data-mode="AU" <?php if ($user["AMode"]=="AU"): ?>disabled<?php endif; ?> class="button AVButton"><i class="mdi <?php if ($user["AMode"]=="AU"): ?>mdi-check-circle<?php else: ?>mdi-circle<?php endif; ?>"></i> Automatisch</button>
            <button type="button" onclick="buttonCheck(this)" data-mode="AV" <?php if ($user["AMode"]=="AV"): ?>disabled<?php endif; ?> class="button AVButton"><i class="mdi <?php if ($user["AMode"]=="AV"): ?>mdi-check-circle<?php else: ?>mdi-circle<?php endif; ?>"></i> Beschikbaar</button>
            <button type="button" onclick="buttonCheck(this)" data-mode="NA" <?php if ($user["AMode"]=="NA"): ?>disabled<?php endif; ?> class="button AVButton"><i class="mdi <?php if ($user["AMode"]=="NA"): ?>mdi-check-circle<?php else: ?>mdi-circle<?php endif; ?>"></i> Niet Beschikbaar</button>
            </div>

            <div style="<?php if($_SESSION["UserData"]["Admin"] == "0"): ?>display: none;<?php endif;?>" class="description">Opties<br>
                <i onclick="toggleCheck(this)" data-enabled="<?=$user["Admin"] ?>" class="togglePermission mdi <?php if ($user["Admin"]=="1"): ?>mdi-check-circle<?php else: ?>mdi-circle-outline<?php endif; ?>"></i> Administrator
                <br><i onclick="toggleCheck(this)" data-enabled="<?=$user["Visible"] ?>" class="togglePermission mdi <?php if ($user["Visible"]=="1"): ?>mdi-check-circle<?php else: ?>mdi-circle-outline<?php endif; ?>"></i> Aanwezigheid tonen op scherm
                <br><i onclick="toggleCheck(this)" data-enabled="<?=$user["Publish"] ?>" class="togglePermission mdi <?php if ($user["Publish"]=="1"): ?>mdi-check-circle<?php else: ?>mdi-circle-outline<?php endif; ?>"></i> Gebruiker mag items publiceren
            </div>
        </form>
        <button type="button" data-userid="<?=$user["UserID"]?>" disabled onclick="userSave(this)" class="button buttonSave" ><i class="mdi mdi-check"></i> Opgeslagen</button>

    </div>

</div>


</body>
</html>
