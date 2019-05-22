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
    <?php
        
        $bookshelfs = checkBookshelfs();
        
        $i = 1;
        for($i = 1; $i <= $bookshelfs; $i++){
            $shelf = showShelf($i);
            if($shelf->num_rows > 0){
    ?>
    <div class="contentBlock">
        <div class="contentBlockTitle">Kast <?php echo $i;?></div>
        <div class="description">
            <table>
                <tr>
                    <th>Rij</th>
                    <th>Kolom</th>
                    <th>Laagste boek nummer</th>
                    <th>Hoogste boek nummer</th>
                </tr>
            <?php
            $table = showShelf($i);
            while($row = $table->fetch_assoc()){?>
                <tr>
                    <td><?php echo $row["Row"];?></td>
                    <td><?php echo $row["Col"];?></td>
                    <td><?php echo $row["lowestBookNr"];?></td>
                    <td><?php echo $row["highestBookNr"];?></td>
                </tr>
            <?php }?>
            </table>
        </div>
        <div class="description">
            <form method="post" action="/views/test.php">
                <button class="button buttonSave" type="submit" value="<?php echo $i; ?>" name="submitButton">Verander kast</button>
            </form>
        </div>
    </div>
            <?php }} ?>
</div>
   


</body>
</html>
