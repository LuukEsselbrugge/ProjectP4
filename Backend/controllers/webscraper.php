<?php
	if(!empty($_POST['search'])){
		$search = $_POST['search'];

		$rss = "http://lbs-nhl.oclc.org:8080//psi_rss/rss_feeds.php?DB=1.5&SEARCH=00yS!i1016!t". $search ."!aY!cN.oY.vD.wD";
        $html = file_get_html($rss);		

		$itemArray = $html->find('item');
		$titleArray = $html->find('item title');
		$descriptionArray = $html->find('item description');
		$max = sizeof($titleArray);


		for($i=0; $i < $max; $i++){
			$book = substr($itemArray[$i]->plaintext, 0, 56);
			$htmlBook = file_get_html($book);

			$bookArray = $htmlBook->find('tr td[.rec_title] div span');
			$sizeBookArray = sizeof($bookArray);
			$Emmmen = 'Emmen ';
			foreach($bookArray as $element){
				if(strpos($element->plaintext, $Emmmen) == true){
					echo $element->plaintext . "<br>";
				}
			}

			// echo $titleArray[$i]->plaintext . "<br>";
			// echo $descriptionArray[$i]->plaintext . "<br><br>";
		}
	}
?>