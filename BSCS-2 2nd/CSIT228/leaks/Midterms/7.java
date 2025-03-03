import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter testcase: ");
        int test = sc.nextInt();
        List<Integer> listInt;
        List<String> listStr;
        List<Double> listDbl;
        try {
            switch (test) {
                case 1:
                    listStr = new ArrayList<>();
                    listStr.add("Orange");
                    listStr.add("Blue");
                    listStr.add("Red");
                    listStr.add("Violet");
                    listStr.add("Yellow");
                    printElements(listStr);
                    break;
                case 2:
                    listInt = new ArrayList<>();
                    listInt.add(5);
                    listInt.add(7);
                    listInt.add(16);
                    listInt.add(28);
                    listInt.add(13);
                    printElements(listInt);
                    break;
                case 3:
                    listDbl = new ArrayList<>();
                    listDbl.add(1.5);
                    listDbl.add(2.7);
                    listDbl.add(3.16);
                    listDbl.add(5.528);
                    listDbl.add(7.13);
                    printElements(listDbl);
                    break;
                case 4:
                    listStr = new ArrayList<>();
                    listStr.add("Orange");
                    listStr.add("Blue");
                    listStr.add("Red");
                    listStr.add("Violet");
                    listStr.add("Yellow");
                    printElements(listStr);
                    swapElements(listStr, 2, 5);
                    printElements(listStr);
                    break;
                case 5:
                    listInt = new ArrayList<>();
                    listInt.add(5);
                    listInt.add(7);
                    listInt.add(16);
                    listInt.add(28);
                    listInt.add(13);
                    printElements(listInt);
                    swapElements(listInt, 0, 4);
                    swapElements(listInt, 2, 1);
                    printElements(listInt);
                    break;
                case 6:
                    listDbl = new ArrayList<>();
                    listDbl.add(1.5);
                    listDbl.add(2.7);
                    listDbl.add(3.16);
                    listDbl.add(5.528);
                    listDbl.add(7.13);
                    printElements(listDbl);
                    swapElements(listDbl, 2, 7);
                    swapElements(listDbl, 1, 3);
                    printElements(listDbl);
                    break;
                case 7:
                    listInt = new ArrayList<>();
                    listInt.add(5);
                    listInt.add(7);
                    listInt.add(16);
                    listInt.add(28);
                    listInt.add(13);
                    printElements(listInt);
                    swapElements(listInt, 2, 1);
                    printElements(listInt);
                    break;
                case 8:
                    listDbl = new ArrayList<>();
                    listDbl.add(1.5);
                    listDbl.add(2.7);
                    listDbl.add(3.16);
                    listDbl.add(5.528);
                    listDbl.add(7.13);
                    printElements(listDbl);
                    swapElements(listDbl, 1, 3);
                    printElements(listDbl);
                    break;
                case 9:
                    listInt = new ArrayList<>();
                    listInt.add(5);
                    listInt.add(7);
                    listInt.add(16);
                    listInt.add(28);
                    listInt.add(13);
                    System.out.println("Max of Ints: " + getMaximum(listInt));
                    break;
                case 10:
                    listDbl = new ArrayList<>();
                    listDbl.add(11.5);
                    listDbl.add(2.7);
                    listDbl.add(3.16);
                    listDbl.add(5.528);
                    listDbl.add(7.13);
                    System.out.println("Max of Doubles: " + getMaximum(listDbl));
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Warning! Do not modify the above code.

    public static <T> void printElements(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
    
    public static <T> void swapElements(List<T> list, int firstIdx, int secondIdx) {
        try {
            T temp = list.get(firstIdx - 1);
            list.set(firstIdx - 1, list.get(secondIdx - 1));
            list.set(secondIdx - 1, temp);
        } catch (Exception e) {
            throw new IllegalArgumentException("Position invalid");
        }
        
        
        
    }
    
    public static <T> T getMaximum(List<T> list) {
        if (list.size() == 0) {
            return null;
        }
        
        T buf = list.get(0);
        
        Number max = null;
        
        if (buf instanceof Number) {
            max = (Number)buf;
        }
        
        for (int i = 1; i < list.size(); i++) {
            buf = list.get(i);
            if (buf instanceof Number) {
                Number buf2 = (Number)buf;
                if (buf2.doubleValue() > max.doubleValue()) {
                    max = buf2;
                }
            }
        }

        if (buf instanceof Number) {
            return (T)max;
        } else {
            return null;
        }
        
    }
}