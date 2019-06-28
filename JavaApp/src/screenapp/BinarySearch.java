package screenapp;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BinarySearch{
    public BinarySearch(){}

    /**
     * Search through the list of shelfs to find the matching shelf for the corresponding book number.
     * @param arr , ArrayList<Shelf> shelfs
     * @param value , String value that you want to find.
     * @return
     */
//    public static int search(ArrayList<Shelf> arr, String value){
//        int index = -1;
//
//        int low = 0;
//        int high = arr.size() -1;
//
//        while(low <= high){
//            int mid = (high + low) / 2;
//
//            // Change the Strings to lowercase all, to prevent uppercase issues.
//            String[] lowBookNr = arr.get(mid).getLowestBookNr().toLowerCase().split(" ");
//            String[] highBookNr = arr.get(mid).getHighestBookNr().toLowerCase().split(" ");
//            String[] valueArray = value.toLowerCase().split(" ");
//
//            // Create a float for the book number and an string for author.
//            float lowBkNrFloat = Float.parseFloat(lowBookNr[0]);
//            String lowBkNrAuthor = lowBookNr[1].substring(0, 3);
//
//            float highBkNrFloat = Float.parseFloat(highBookNr[0]);
//            String highBkNrAuthor = highBookNr[1].substring(0, 3);
//
//            float valueFloat = Float.parseFloat(valueArray[0]);
//            String valueAuthor = valueArray[1].substring(0, 3);
//
//            // Make a decimalformat to create generic Strings.
//            DecimalFormat df = new DecimalFormat("0.0");
//
//            // Make all book ID's uniform.
//            String lowestBook = (df.format(lowBkNrFloat) + lowBkNrAuthor).toLowerCase();
//            String highestBook = (df.format(highBkNrFloat) + highBkNrAuthor).toLowerCase();
//            String searchValue = (df.format(valueFloat) + valueAuthor).toLowerCase();
//
//            if(lowestBook.compareTo(searchValue) < 0 && highestBook.compareTo(searchValue) < 0){
//                low = mid + 1;
//            }else if(lowestBook.compareTo(searchValue) > 0 && highestBook.compareTo(searchValue) > 0){
//                high = mid -1;
//            }else if(lowestBook.compareTo(searchValue) == 0 || highestBook.compareTo(searchValue) == 0 ||
//                    (lowestBook.compareTo(searchValue) < 0 && highestBook.compareTo(searchValue) > 0)){
//                index = mid;
//                break;
//            }
//        }
//        return index;
//    }

    /**
     * Search through the list of shelfs to find the matching shelf for the corresponding book number.
     * @param arr , ArrayList<Shelf> shelfs
     * @param value , String value that you want to find.
     * @return
     */
    public static int sequential(ArrayList<Shelf> arr, String value){
        int index  = -1;

        for (int i = 0; i < arr.size(); i++){
            // Change the Strings to lowercase all, to prevent uppercase issues.
            String[] lowBookNr = arr.get(i).getLowestBookNr().toLowerCase().split(" ");
            String[] highBookNr = arr.get(i).getHighestBookNr().toLowerCase().split(" ");
            String[] valueArray = value.toLowerCase().split(" ");

            // Create a float for the book number and an string for author.
            float lowBkNrFloat = Float.parseFloat(lowBookNr[0]);
            String lowBkNrAuthor = lowBookNr[1].substring(0, 3);

            float highBkNrFloat = Float.parseFloat(highBookNr[0]);
            String highBkNrAuthor = highBookNr[1].substring(0, 3);

            float valueFloat = Float.parseFloat(valueArray[0]);
            String valueAuthor = valueArray[1].substring(0, 3);

            // Make a decimalformat to create generic Strings.
            DecimalFormat df = new DecimalFormat("0.0");

            // Make all book ID's uniform.
            String lowestBook = (df.format(lowBkNrFloat) + lowBkNrAuthor).toLowerCase();
            String highestBook = (df.format(highBkNrFloat) + highBkNrAuthor).toLowerCase();
            String searchValue = (df.format(valueFloat) + valueAuthor).toLowerCase();

            if(lowestBook.compareTo(searchValue) == 0 || highestBook.compareTo(searchValue) == 0 || (lowestBook.compareTo(searchValue) < 0 && highestBook.compareTo(searchValue) > 0)){
                index = i;
            }
        }
        return index;
    }
}