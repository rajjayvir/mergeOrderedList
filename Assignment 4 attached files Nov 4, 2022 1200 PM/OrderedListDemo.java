/**
 * Author: Jayvirsinh Raj
 * B00907110
 * <p>
 * This is the demo class where I have read a list from the text file and implemented different method on the list
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OrderedListDemo {
    public static void main(String[] args) throws IOException {

        //initialize a scanner class
        Scanner kb = new Scanner(System.in);

        //read the name of the file (list 1)
        System.out.print("Enter the first filename to read from: ");
        String file1 = kb.nextLine();
        //convert the name of the list to a file that is read in the string above
        Scanner firstFile = new Scanner(new File(file1));

        //read the other file (list 2 )
        System.out.print("Enter the second filename to read from: ");
        String file2 = kb.nextLine();
        //convert the name of the lxist to a file that is read in the string above
        Scanner secondFile = new Scanner(new File(file2));

        //a list where the first list is initialized where the file 1 is saved
        OrderedList<String> first = new OrderedList<>();

        //saved the file 1 inputs to the first list
        while (firstFile.hasNextLine()) {
            String input = firstFile.nextLine();
            first.insert(input);
        }

        //a list where the second list is initialized where the file 2 is saved
        OrderedList<String> second = new OrderedList<>();

        //saved the file 1 inputs to the first list
        while (secondFile.hasNextLine()) {
            String input = secondFile.nextLine();
            second.insert(input);
        }

        //merge 2 lists and forms another list and saves it in a txt doc
//        System.out.println("merge");
//        merge(first, second).enumerate();
        StringBuilder merger = new StringBuilder();
        for (int i = 0; i < merge(first, second).size(); i++) {
            merger.append(merge(first, second).get(i)).append("\n");
        }
        //writes the ordered list in a file named merged.txt. here a new file is generated in the folder
        FileWriter fileNameMerge = new FileWriter("merged.txt");
        fileNameMerge.write(String.valueOf(merger));
        fileNameMerge.close();


        //find the different items that are in list1 and not in list2
//        System.out.println("difference");
//        difference(first, second).enumerate();
        StringBuilder differences = new StringBuilder();
        for (int i = 0; i < difference(first, second).size(); i++) {
            differences.append(difference(first, second).get(i)).append("\n");
        }

        //writes the ordered list in a file name difference.txt.
        FileWriter fileNameDifference = new FileWriter("difference.txt");
        fileNameDifference.write(String.valueOf(differences));
        fileNameDifference.close();

        //write the ordered list in a file named common.txt. That is the items in both list1 and list2
//        System.out.println("common");
//        common(first, second).enumerate();
        StringBuilder common = new StringBuilder();
        for (int i = 0; i < common(first, second).size(); i++) {
            common.append(common(first, second).get(i)).append("\n");
        }

        FileWriter fileNameCommon = new FileWriter("common.txt");
        fileNameCommon.write(String.valueOf(common));
        fileNameCommon.close();

        System.out.println("The merge operations are complete and the results are in merged.txt, difference.txt and common.txt");
    }

    /**
     * This method merges 2 ordered list and the merged list is also the an orderd list
     *
     * @param list1 - the ordered list that is list that is input to be merged
     * @param list2 - the ordered list that is list that is input to be merged
     * @param <T>   - any data type can be input
     * @return - a merged list of list1 and list2
     */
    public static <T extends Comparable<T>> OrderedList<T> merge(OrderedList<T> list1, OrderedList<T> list2) {

        //the list that is to be returned
        OrderedList<T> mergedList = new OrderedList<>();
        int f1 = 0, f2 = 0;

        //applied 2 finger walking algorithm here
        while (f1 < list1.size() && f2 < list2.size()) {
            if ((list1.get(f1).compareTo(list2.get(f2))) < 0) {
                mergedList.add(list1.get(f1));
                f1++;
            } else if ((list1.get(f1).compareTo(list2.get(f2))) > 0) {
                mergedList.add(list2.get(f2));
                f2++;
            } else {
                mergedList.add(list1.get(f1));
                f1++;
                f2++;
            }
        }

        //when the list comes to an end
        if (f2 == list2.size()) {
            for (int i = f1; i < list1.size(); i++) {
                mergedList.add(list1.get(i));
            }
        }
        if (f1 == list1.size()) {
            for (int i = f2; i < list2.size(); i++) {
                mergedList.add(list2.get(i));
            }
        }
        return mergedList;
    }

    /**
     * This method find the items that are in list 1 but not in list 2
     *
     * @param list1 -  the ordered list that is list that is input to be merged
     * @param list2 -  the ordered list that is list that is input to be merged
     * @param <T>   -any data type can be input
     * @return - an ordered list of any data type that contains the elements in list 1 but not in list 2
     */
    public static <T extends Comparable<T>> OrderedList<T> difference(OrderedList<T> list1, OrderedList<T> list2) {
        OrderedList<T> differenceList = new OrderedList<>();
        int f1 = 0, f2 = 0;

        while (f1 < list1.size() && f2 < list2.size()) {
            if (list1.get(f1).compareTo(list2.get(f2)) > 0) {
                f2++;
            } else if (list1.get(f1).compareTo(list2.get(f2)) < 0) {
                differenceList.add(list1.get(f1));
                f1++;
            } else if (list1.get(f1).compareTo(list2.get(f2)) == 0) {
                f1++;
                f2++;
            }
        }

        if (f2 == list2.size()) {
            for (int i = f1; i < list1.size(); i++) {
                differenceList.add(list1.get(i));
            }
        }
        return differenceList;
    }

    /**
     * This method finds the elements that are common in list 1 and list 2
     *
     * @param list1 -  the ordered list that is list that is input to be merged
     * @param list2 -  the ordered list that is list that is input to be merged
     * @param <T>   - any data type can be input
     * @return - an ordered list with the common elements from the list 1 and list 2
     */
    public static <T extends Comparable<T>> OrderedList<T> common(OrderedList<T> list1, OrderedList<T> list2) {

        OrderedList<T> commonList = new OrderedList<>();
        int f1 = 0, f2 = 0;


        while (f1 < list1.size() && f2 < list2.size()) {
            if (list1.get(f1).compareTo(list2.get(f2)) > 0) {
                f2++;
            } else if (list1.get(f1).compareTo(list2.get(f2)) < 0) {
                f1++;
            } else if (list1.get(f1).compareTo(list2.get(f2)) == 0) {
                commonList.add(list1.get(f1));
                f1++;
                f2++;
            }
        }

        if (f2 == list2.size()) {
            for (int i = f1; i < list1.size(); i++) {
                commonList.add(list1.get(i));
            }
        }
        return commonList;
    }
}

