<!DOCTYPE html>
<html lang="en">
<head>
    <title>LibrarySearch</title>
    <?php include_once('head.php') ?>

</head>
<body>

<?php include_once('navigation.php') ?>
<?php require_once('getShelfs.php') ?>

<div class="topMenuSecond">
    <div class="topMenuInner">
        <div class="topMenuTitle">Overzicht / <b>Kasten</b></div>
    </div>
</div>

<div class="content">
    <div class="contentBlock">
    <div class="contentBlockTitle">Kast <?php echo $_POST["submitButton"];?></div>
        <div class="description">
            <table>
                <tr>
                    <th>Rij</th>
                    <th>Kolom</th>
                    <th>Laagste boek nummer</th>
                    <th>Hoogste boek nummer</th>
                    <th>Pas Plank aan</th>
                </tr>
            <?php
            $table = showShelf($_POST["submitButton"]);
            while($row = $table->fetch_assoc()){?>
                <tr>
                    <td><input required class="contentInput" type="text" value="<?php echo $row["Row"];?>"></td>
                    <td><input required class="contentInput" type="text" value="<?php echo $row["Col"];?>"></td>
                    <td><input required class="contentInput" type="text" value="<?php echo $row["lowestBookNr"];?>"></td>
                    <td><input required class="contentInput" type="text" value="<?php echo $row["highestBookNr"];?>"></td>
                    <td><button class="button buttonSave" type="submit" value="<?php echo $row["shelfID"]; ?>" name="shelfButton">Plank Aanpassen</button></td>
                </tr>
            <?php
                }
            ?>
            </table>
        </div>
    </div>
</div>
<?php
    
?>


</body>
</html>
