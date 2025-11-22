package pl.mg.javatst.codility;

/**
 *
 * 100%
 */
public class DistinctSort {

  public static void main(String[] args) {
    int[] integers = {2, 1, 1, 2, 3, 1};
    DistinctSort distinctSort = new DistinctSort();
    System.out.println(distinctSort.solution(integers));
  }

  public static void sort(int arr[], int low, int high) {
    if (low < high) {
      /* pi is partitioning index, arr[pi] is
      now at right place */
      int pi = partition(arr, low, high);

      // Recursively sort elements before
      // partition and after partition
      sort(arr, low, pi - 1);
      sort(arr, pi + 1, high);
    }
  }

  public static int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = (low - 1); // index of smaller element
    for (int j = low; j < high; j++) {
      // If current element is smaller than or
      // equal to pivot
      if (arr[j] <= pivot) {
        i++;

        // swap arr[i] and arr[j]
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    // swap arr[i+1] and arr[high] (or pivot)
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
  }

  public int solution(int[] A) {
    int result = 1;
    sort(A, 0, A.length - 1);
    for (int j = 0; j < (A.length - 1); j++) {
      if (A[j] != A[j + 1]) {
        result++;
      }
    }
    return result;
  }
}
