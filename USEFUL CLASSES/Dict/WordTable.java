package com.michael.projects;

import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class WordTable extends HashMap<Integer, String>{
    public void removeDuplicates(){
        for (int i = this.size(); i >0; i--) {
            if (Collections.frequency(this.values(), this.get(i)) > 1){
                System.out.println(i);
                this.remove(i);

            }
        }
    }


}
