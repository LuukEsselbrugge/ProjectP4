package screenapp;

import java.util.ArrayList;

public class BinarySearch{
    public BinarySearch(){}

    /**
     * Search through the list of shelfs to find the matching shelf for the corresponding book number.
     * @param arr , ArrayList<Shelf> shelfs
     * @param value , String value that you want to find.
     * @param low , lowest index number.
     * @param high , highest index number.
     * @return
     */
    public static int search(ArrayList<Shelf> arr, String value, int low, int high){
        int index = -1;

        while(low <= high){
            int mid = high + low / 2;


            // Change the Strings to lowercase all, to prevent uppercase issues.
            String lowBookNr = arr.get(mid).getLowestBookNr().toLowerCase();
            String highBookNr = arr.get(mid).getHighestBookNr().toLowerCase();
            value = value.toLowerCase();

            // Compare to 2 values to get the matching shelf.
            if(lowBookNr.compareTo(value) < 0 && highBookNr.compareTo(value) < 0){
                low = mid + 1;
            }else if(lowBookNr.compareTo(value) > 0 && highBookNr.compareTo(value) > 0){
                high = mid -1;
            }else if(lowBookNr.compareTo(value) == 0 || highBookNr.compareTo(value) == 0 || (lowBookNr.compareTo(value) > 0 && highBookNr.compareTo(value) < 0)){
                index = mid;
                break;
            }
        }
        return index;
    }
}