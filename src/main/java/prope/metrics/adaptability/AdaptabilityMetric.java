package prope.metrics.adaptability;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import prope.metrics.adaptability.elements.AdaptableElement;
import prope.metrics.adaptability.elements.AdaptableElements;

abstract class AdaptabilityMetric {
	
	final int referenceScore;

	final Map<String, AdaptableElement> elements;
	
	AtomicInteger sum;

	AtomicInteger maxDegree;

	
	public AdaptabilityMetric(){
		elements = new AdaptableElements().getElementsByName();
		referenceScore = elements.values().parallelStream()
				.filter(element -> !element.isForDetectionOnly())
				.mapToInt(element -> element.getAdaptabilityScore()).max()
				.getAsInt();
	}
	
	public abstract double computeAdaptability(Map<String, AtomicInteger> processElements);

	public abstract String getIdentifier();

    int getReferenceScore() {
		return referenceScore;
	}
    
    Map<String, AdaptableElement> getElements(){
    	return elements;
    }


}
