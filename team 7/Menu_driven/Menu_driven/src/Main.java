import java.util.*;

// 1. Frequency Of Elements
class FrequencyOfElements
{
    static void findFrequency(int[] arr)
    {
        Arrays.sort(arr);
        int count = 1;

        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] == arr[i - 1])
                count++;
            else
            {
                System.out.println(arr[i - 1] + " -> " + count);
                count = 1;
            }
        }
        System.out.println(arr[arr.length - 1] + " -> " + count);
    }
}

// 2. Missing Number
class MissingNumber
{
    static int findMissing(int[] arr, int n)
    {
        boolean[] present = new boolean[n + 1];

        for (int i = 0; i < arr.length; i++)
            present[arr[i]] = true;

        for (int i = 1; i <= n; i++)
        {
            if (!present[i])
                return i;
        }
        return -1;
    }
}

// 3. Move Zeros To End
class MoveZerosToEnd
{
    static void moveZeros(int[] arr)
    {
        int index = 0;

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] != 0)
                arr[index++] = arr[i];
        }

        while (index < arr.length)
            arr[index++] = 0;
    }
}

// 4. Remove Duplicates
class RemoveDuplicates
{
    static int removeDuplicates(int[] arr)
    {
        Arrays.sort(arr);
        int j = 0;

        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] != arr[j])
                arr[++j] = arr[i];
        }
        return j + 1;
    }
}

// 5. Second Largest Element
class SecondLargeElement
{
    static int findSecondLargest(int[] arr)
    {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] > largest)
            {
                secondLargest = largest;
                largest = arr[i];
            }
            else if (arr[i] > secondLargest && arr[i] != largest)
            {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }
}

// 6. Main Class (Menu Driven)
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Frequency Of Elements");
        System.out.println("2. Missing Number");
        System.out.println("3. Move Zeros To End");
        System.out.println("4. Remove Duplicates");
        System.out.println("5. Second Largest Element");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice)
        {
            case 1:
                System.out.print("Enter array size: ");
                int n1 = sc.nextInt();
                int[] arr1 = new int[n1];

                System.out.println("Enter elements:");
                for (int i = 0; i < n1; i++)
                    arr1[i] = sc.nextInt();

                FrequencyOfElements.findFrequency(arr1);
                break;

            case 2:
                System.out.print("Enter n value: ");
                int n2 = sc.nextInt();
                int[] arr2 = new int[n2 - 1];

                System.out.println("Enter elements:");
                for (int i = 0; i < n2 - 1; i++)
                    arr2[i] = sc.nextInt();

                System.out.println("Missing Number: " +
                        MissingNumber.findMissing(arr2, n2));
                break;

            case 3:
                System.out.print("Enter array size: ");
                int n3 = sc.nextInt();
                int[] arr3 = new int[n3];

                System.out.println("Enter elements:");
                for (int i = 0; i < n3; i++)
                    arr3[i] = sc.nextInt();

                MoveZerosToEnd.moveZeros(arr3);

                System.out.println("Result:");
                for (int i = 0; i < n3; i++)
                    System.out.print(arr3[i] + " ");
                break;

            case 4:
                System.out.print("Enter array size: ");
                int n4 = sc.nextInt();
                int[] arr4 = new int[n4];

                System.out.println("Enter elements:");
                for (int i = 0; i < n4; i++)
                    arr4[i] = sc.nextInt();

                int len = RemoveDuplicates.removeDuplicates(arr4);

                System.out.println("Array after removing duplicates:");
                for (int i = 0; i < len; i++)
                    System.out.print(arr4[i] + " ");
                break;

            case 5:
                System.out.print("Enter array size: ");
                int n5 = sc.nextInt();
                int[] arr5 = new int[n5];

                System.out.println("Enter elements:");
                for (int i = 0; i < n5; i++)
                    arr5[i] = sc.nextInt();

                System.out.println("Second Largest Element: " +
                        SecondLargeElement.findSecondLargest(arr5));
                break;

            default:
                System.out.println("Invalid Choice");
        }
    }
}
