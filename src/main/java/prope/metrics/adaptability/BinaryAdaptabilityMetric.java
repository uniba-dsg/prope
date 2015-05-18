package prope.metrics.adaptability;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import prope.metrics.adaptability.elements.AdaptableElement;

final class BinaryAdaptabilityMetric extends AdaptabilityMetric {

	private final double cutoffValue;

	public BinaryAdaptabilityMetric(double cutoffValue) {
		this.cutoffValue = cutoffValue;
	}

	@Override
	public double computeAdaptability(Map<String, AtomicInteger> processElements) {
		sum = new AtomicInteger(0);
		maxDegree = new AtomicInteger(0);
		processElements.keySet().forEach(
				elementName -> addElementAdaptability(processElements,
						elementName));
		if (maxDegree.get() == 0) {
			return -1;
		} else {
			return sum.doubleValue() / maxDegree.doubleValue();
		}
	}

	private void addElementAdaptability(
			Map<String, AtomicInteger> processElements, String elementName) {
		AdaptableElement element = elements.get(elementName);
		if (element.isForDetectionOnly()) {
			return;
		}

		int number = processElements.get(elementName).get();
		int elementScore = element.getAdaptabilityScore();

		if (elementScore >= (referenceScore * cutoffValue)) {
			sum.addAndGet(number);
		}

		maxDegree.addAndGet(number);
	}

	@Override
	public String getIdentifier() {
		return "AD" + cutoffValue;
	}

}
