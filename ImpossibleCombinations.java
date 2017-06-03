package kempot.domparser;

import java.util.ArrayList;
import java.util.List;

public class ImpossibleCombinations {
	
	public String[][] pairs = {
	        {"Indonesia", "Winter"},
	        {"Indonesia", "Summer"},
	        {"Indonesia", "Autumn"},
	        {"Indonesia", "Spring"},
	        {"Winter", "Indonesia"},
	        {"Summer", "Indonesia"},
	        {"Autumn", "Indonesia"},
	        {"Spring", "Indonesia"},
	        
//	        {"Indonesia", "Northern"},
//	        {"Indonesia", "Southern"},
//	        {"Northern", "Indonesia"},
//	        {"Southern", "Indonesia"},
//	        
//	        {"US", "Southern"},
//	        {"Southern", "US"},
	        
	        {"US", "Dry"},
	        {"US", "Rain"},
	        {"Dry", "US"},
	        {"Rain", "US"},
	        
	        {"Children", "Farmers"},
	        {"Children", "Military"},
	        {"Farmers", "Children"},
	        {"Military", "Children"},
	    };
	private int addingLocationIndex = 0;
	
	NetConverter count;
	
	public ImpossibleCombinations(){
		
	}

    /**
     * generate pairs
     */
    public boolean checkValidPermutation(ArrayList<String> permutation) {
        // for each impossible pair in all pairs
        for(String[] impossiblePair : pairs) {
            // set currently matched items between impossible pair 
            // and row (or permutation we are checking) to 0, nothing found
            int matchedItems = 0;
            // iterate over each item, and if it is present in the row
            // or permutation, increment the matched item count
            for(String item : impossiblePair) {
                if (permutation.contains(item)) {
                    matchedItems += 1;
                }
            }

            if (matchedItems == impossiblePair.length) {
                return false;
            }
        }
        // no impossible conditions were found, therefore, return true
        return true;
    }
    /** END **/
	
}
