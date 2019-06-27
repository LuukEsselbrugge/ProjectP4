<!DOCTYPE html>
<html lang="en">
<head>
    <title>LibrarySearch</title>
    <?php include_once('head.php') ?>

</head>
<body>

<?php include_once('navigation.php') ?>


<div class="topMenuSecond">
    <div class="topMenuInner">
        <div class="topMenuTitle">Beheren / <b>Gebruikers</b></div>
    </div>
</div>

<div class="content">
    <div class="contentBlock">
        <div class="description">Beheer de gebruikers die van het dashboard gebruik kunnen maken</div>
    <button onclick="userAdd(this)" class="button" ><i class="mdi mdi-playlist-plus"></i> Gebruiker toevoegen</button>

    </div>

    <?php include_once('alert.php') ?>

    <div class="contentBlock">
    <?php foreach ($users as $submission) : ?>
     <div onclick="window.location='users/<?=$submission["UserID"]?>'" class="contentBlock cursorhand">

            <div style="<?php if($submission["Image"]!=""): ?>  background-image: url('/images/<?=$submission["Image"]?>'); font-size: 0px;  <?php endif; ?>" class="UserImage"><?=$submission["NameCode"]?></div> 
            <div class="userName"><?=$submission["Firstname"]?> <?=$submission["Lastname"]?></div>

            <div onclick="confirmbox('Wil je deze gebruiker verwijderen?','users/delete/?id=<?=$submission["UserID"]?>',event); event.stopPropagation();" class="deleteButton"><i class="mdi mdi-delete "></i></div>

            <?php
            $mode = $submission["AMode"];
            if($mode == "AU")
                $mode = "Automatisch";
            elseif($mode == "AV")
                $mode = "Beschikbaar";
            else
                $mode = "Niet beschikbaar";
            
            echo "<div style='float: right; margin-right: 50px'>{$mode}</div>";
            ?>

        </div>
    <?php endforeach; ?>
    </div>

</div>


</body>
</html>
