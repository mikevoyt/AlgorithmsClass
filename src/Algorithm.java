import java.util.ArrayList;
import java.util.List;

/**
 */
public class Algorithm {
	public static int countInversions(List<Integer> values) {
		if (values == null) return 0;

		if (values.size() == 2) {
			return (values.get(0) < values.get(1) ? 0 : 1);
		}

		int half = values.size()/2;
		if (half == 0 || half == 1) return 0;

		List<Integer> left = values.subList(0, half);
		List<Integer> right = values.subList(half, values.size());

		int leftCount = countInversions(left);
		int rightCount = countInversions(right);

		return leftCount + rightCount + splitCount(left, right);
	}
	
	public static int splitCount(List<Integer> leftList, List<Integer> rightList) {
		int count = 0;
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = 0; i < leftList.size() + rightList.size(); i++) {

			Integer left;
			Integer right;

			if (leftIndex < leftList.size()) {
				left = leftList.get(leftIndex);
			} else {
				break;
			}
			if (rightIndex < rightList.size()) {
				right = rightList.get(rightIndex);
			} else {
				count += leftList.size()-leftIndex;
				break;
			}

			if (left < right) {
				leftIndex++;
			} else {
				count++;
				rightIndex++;
			}
		}
		return count;
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
