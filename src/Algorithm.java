import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    private static long comparisons;
    public static List<Integer> quickSortWrapper(List<Integer> values, int left, int right) {
        comparisons = 0;
        List<Integer> ret = quickSort(values, left, right);
        System.out.println("comparisons: " + comparisons);
        return ret;
    }
    
	public static List<Integer> quickSort(List<Integer> values, int left, int right) {
        if (right - left <= 1) return values;
        setPivot(PivotType.MEDIAN_OF_THREE, values, left, right);
        int pivotIndex = partition(values, left, right);
        quickSort(values, left, pivotIndex-1);
        //comparisons += (pivotIndex-1 - left);
        quickSort(values, pivotIndex, right);
        //comparisons += right - pivotIndex;
        return values;
	}

    private static int partition(List<Integer> array, int left, int right) {
        final int pivot = array.get(left);
        int i = left+1;
        for (int j = i; j < right; j++) {
            comparisons++;
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
        return i;
    }

    private enum PivotType {
        FIRST_ELEMENT,
        LAST_ELEMENT,
        MEDIAN_OF_THREE
    }

    private static void setPivot(PivotType type, List<Integer> array, int left, int right) {
        switch (type) {
            case FIRST_ELEMENT:
                return;
            case LAST_ELEMENT:
            {
                //swap last into first
                int last = array.get(right-1);
                array.set(right-1, array.get(left));
                array.set(left, last);
                return;
            }
            case MEDIAN_OF_THREE:
            {
                int median = 0;
                int first = array.get(left);
                int last = array.get(right-1);
                int mid = array.get(left + (((right-1)-left))/2);
                if ((last <= first && last >= mid) || ((last <= mid && last >= first))) {
                    median = last;
                    array.set(right-1, array.get(left));
                    array.set(left, median);
                }
                else if ((mid <= last && mid >= first) || ((mid <= first && mid >= last))) {
                    median = mid;
                    array.set(left + (((right-1)-left))/2, array.get(left));
                    array.set(left, median);
                }
                else if ((first <= last && first >= mid) || ((first <= mid && first >= last))) {
                    median = first;
                    //nothing to do
                }
                else throw new RuntimeException("yikes, didn't find median!!");


                //System.out.println(array.subList(left, right) + String.format(": first=%d, mid=%d, last=%d, median=%d",
                //        first, mid, last, median));
                return;

            }
            default:
                return;
        }
    }

}
