<?php
	include_once("simple_html_dom.php");
class webscraperController{
    private $db;

	public function getShelfs(){
		$this->db = (new Database())->connect();
        $sql = $this->db->prepare("SELECT * FROM Shelf");
        $status = $sql->execute(array());
        if (!$status) {
            echo "Something went wrong. Please try again later";
            die();
        }
        $books = $sql->fetchall(PDO::FETCH_ASSOC);

		$json = json_encode($books);
		echo $json;
	}

	public function getResults(){
		if(!empty($_POST['search'])){

			$search = $_POST['search'];

			$search = str_replace(" ", "+", $search);
			
			$rss = 'http://lbs-nhl.oclc.org:8080//psi_rss/rss_feeds.php?DB=1.5&SEARCH=00yS!i1016!t'. $search .'!aY!cN.oY.vD.wD';
			$html = file_get_html($rss);			
	
			$itemArray = $html->find('item');		
			$titleArray = $html->find('item title');
			$descriptionArray = $html->find('item description');
			$max = sizeof($titleArray);
	
	
			for($i=0; $i < $max; $i++){
				$book = substr($itemArray[$i]->plaintext, 0, 56);
				$htmlBook = file_get_html($book);
	
				$value = "";
				$bookArray = $htmlBook->find('div.lrmargin div table tr td.rec_title div span');
				$sizeBookArray = sizeof($bookArray);
				$regex = "/Emmen /";
				foreach($bookArray as $element){
					if(preg_match($regex ,$element->innertext)){
						$value = explode(" ", $element->innertext);
						$number = $value[1] . " " . $value[2];
						break;
					}
				}
	
				$reserverdArray = $htmlBook->find('div  ');
				$title = $titleArray[$i]->plaintext;
				$description = $descriptionArray[$i]->plaintext;
	
				$values[] = array(
					'number' => $number,
					'title' => $title,
					'description' => $description
				);
			}
			$json = json_encode($values);
			echo $json;
		}	
	}
}	
?>
