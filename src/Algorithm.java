import java.util.ArrayList;
import java.util.List;

/**
 */
public class Algorithm {
	
	static long inversions = 0;
	
	public static long getInversions() {
		return inversions;
	}
	
	public static List<Integer> countInversions(List<Integer> values) {
		if (values == null) return null;
		if (values.size() == 0 || values.size() == 1) return values;

		int half = values.size()/2;
		List<Integer> left = values.subList(0, half);
		List<Integer> right = values.subList(half, values.size());

		List<Integer> leftSorted = countInversions(left);
		List<Integer> rightSorted = countInversions(right);

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
				inversions += (leftList.size() - leftIndex);
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
}
