<div class="navBar">
    <div class="navBarContent">

        <div class="navBarTitle">
            Sboard
        </div>

        <a href="/availability" >
            <div class="navBarItem">
                <i class="mdi mdi-clock"></i>
            </div>
        </a>

        <a href="/submissions" >
            <div class="navBarItem">
                <i class="mdi mdi-view-list"></i>
            </div>
        </a>

        <a href="/board" >
            <div class="navBarItem">
                <i class="mdi mdi-television-guide"></i>
            </div>
        </a>
        <?php if (isset($_SESSION["UserData"]["Email"])): ?>
                <div onclick="" class="navBarItem navBarItemRight">
                    <div style="<?php if($_SESSION["UserData"]["Image"]!=""): ?>  background-image: url('/images/<?=$_SESSION["UserData"]["Image"]?>'); font-size: 0px;  <?php endif; ?>" class="UserImage"><?=$_SESSION["UserData"]["NameCode"]?></div> <?=$_SESSION["UserData"]["Firstname"]?> <?=$_SESSION["UserData"]["Lastname"]?> <i class="material-icons">arrow_drop_down</i>
                    <div class="dropDown">
                        <div onclick="window.location='/users/<?=$_SESSION["UserData"]["UserID"]?>'" class="dropDownItem"><i class="mdi mdi-settings"></i> Instellingen</div>
                        <?php if($_SESSION["UserData"]["Admin"] == "1"): ?>
                        <div onclick="window.location='/system'"  class="dropDownItem"><i class="mdi mdi-settings"></i> Systeem</div>
                        <div onclick="window.location='/users'" class="dropDownItem"><i class="mdi mdi-account"></i> Gebruikers</div>
                        <?php endif; ?>
                        <div onclick="window.location='/session/logout'" class="dropDownItem"><i class="mdi mdi-logout"></i> Uitloggen</div>
                    </div>
                </div>

        <?php endif; ?>

    </div>
</div>