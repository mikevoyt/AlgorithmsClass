import java.util.ArrayList;
import java.util.List;

/**
 */
public class Algorithm {
	
	private static long inversions = 0;

	public static long countInversions(List<Integer> values) {
		inversions = 0;
		countSortInversions(values);
		return inversions;
	}


	private static List<Integer> countSortInversions(List<Integer> values) {
		if (values == null) return null;
		if (values.size() == 0 || values.size() == 1) return values;

		int half = values.size()/2;
		List<Integer> left = values.subList(0, half);
		List<Integer> right = values.subList(half, values.size());

		List<Integer> leftSorted = countSortInversions(left);
		List<Integer> rightSorted = countSortInversions(right);

		return countSplitInversions(leftSorted, rightSorted);
	}


	private static List<Integer> countSplitInversions(List<Integer> leftList, List<Integer> rightList) {
		List<Integer> sortedList = new ArrayList<Integer>();
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = 0; i < leftList.size() + rightList.size(); i++) {

			Integer left;
			Integer right;

			if (leftIndex < leftList.size()) {
				left = leftList.get(leftIndex);
			} else {
				//leftList is done; concat right list to result and we're done
				sortedList.addAll(rightList.subList(rightIndex, rightList.size()));
				break;
			}
			if (rightIndex < rightList.size()) {
				right = rightList.get(rightIndex);
			} else {
				//rightList is done; concat left list to result and we're done
				sortedList.addAll(leftList.subList(leftIndex, leftList.size()));
				break;
			}

			if (left < right) {
				sortedList.add(left);
				leftIndex++;
			} else {
				sortedList.add(right);
				rightIndex++;
				inversions += (leftList.size() - leftIndex);  //# of inversions is total remaining in left list
			}
		}
		return sortedList;
	}
	
	public static List<Integer> mergeSort(List<Integer> values) {
		if (values == null) return null;
		if (values.size() == 0 || values.size() == 1) return values;

		int half = values.size()/2;
		List<Integer> left = values.subList(0, half);
		List<Integer> right = values.subList(half, values.size());
		
		List<Integer> leftSorted = mergeSort(left);
		List<Integer> rightSorted = mergeSort(right);
		
		return sort(leftSorted, rightSorted);
	}
	
	private static List<Integer> sort(List<Integer> leftList, List<Integer> rightList) {
		List<Integer> sortedList = new ArrayList<Integer>();
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = 0; i < leftList.size() + rightList.size(); i++) {

			Integer left;
			Integer right;

			if (leftIndex < leftList.size()) {
				left = leftList.get(leftIndex);				
			} else {
				//leftList is done; concat right list to result and we're done
				sortedList.addAll(rightList.subList(rightIndex, rightList.size()));
				break;
			}
			if (rightIndex < rightList.size()) {
				right = rightList.get(rightIndex);
			} else {
				//rightList is done; concat left list to result and we're done
				sortedList.addAll(leftList.subList(leftIndex, leftList.size()));
				break;
			}

			if (left < right) {
			sortedList.add(left);
			leftIndex++;
			} else {
				sortedList.add(right);
				rightIndex++;
			}
		}
		 return sortedList;
	}

	public static List<Integer> quickSort(List<Integer> values) {
        return null;
	}

    private static void partition(List<Integer> array, int left, int right) {
        final int pivot = array.get(left);
        int i = left+1;
        for (int j = i; j < right; j++) {
            if (array.get(j) < pivot) {
                int tmp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, tmp);
                i++;
            }
        }

        int tmp = array.get(left);
        array.set(left, array.get(i-1));
        array.set(i-1, tmp);
    }

}
