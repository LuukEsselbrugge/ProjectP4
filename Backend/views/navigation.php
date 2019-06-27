<div class="navBar">
    <div class="navBarContent">

        <a href="/">
            <div class="navBarTitle">
                LibrarySearch
            </div>
        </a>

        <?php if (isset($_SESSION["UserData"]["Email"])): ?>
                <div onclick="" class="navBarItem navBarItemRight">
                    <div style="<?php if($_SESSION["UserData"]["Image"]!=""): ?>  background-image: url('/images/<?=$_SESSION["UserData"]["Image"]?>'); font-size: 0px;  <?php endif; ?>" class="UserImage"><?=$_SESSION["UserData"]["NameCode"]?></div> <?=$_SESSION["UserData"]["Firstname"]?> <?=$_SESSION["UserData"]["Lastname"]?> <i class="material-icons">arrow_drop_down</i>
                    <div class="dropDown">
                        <div onclick="window.location='/users/<?=$_SESSION["UserData"]["UserID"]?>'" class="dropDownItem"><i class="mdi mdi-settings"></i> Instellingen</div>
                        <div onclick="window.location='/session/logout'" class="dropDownItem"><i class="mdi mdi-logout"></i> Uitloggen</div>-->
                    </div>
                </div>

        <?php endif; ?>

    </div>
</div>