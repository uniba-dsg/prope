package prope.metrics.adaptability;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import prope.metrics.adaptability.elements.AdaptableElement;

final class WeightedAdaptabilityMetric extends AdaptabilityMetric {

	@Override
	public double computeAdaptability(Map<String, AtomicInteger> processElements) {
		sum = new AtomicInteger(0);
		maxDegree = new AtomicInteger(0);
		processElements.keySet().forEach(
				elementName -> addElementAdaptability(processElements,
						elementName));
		if (maxDegree.get() == 0) {
			return 0;
		} else {
			return sum.doubleValue() / maxDegree.doubleValue();
		}
	}

	private void addElementAdaptability(
			Map<String, AtomicInteger> processElements, String elementName) {
		AdaptableElement element = getElements().get(elementName);
		if (element.isForDetectionOnly()) {
			return;
		}

		int number = processElements.get(elementName).get();
		int elementScore = element.getAdaptabilityScore();
		sum.addAndGet(number * elementScore);
		maxDegree.addAndGet(number * getReferenceScore());
	}

	@Override
	public String getIdentifier() {
		return "wAD";
	}
}
