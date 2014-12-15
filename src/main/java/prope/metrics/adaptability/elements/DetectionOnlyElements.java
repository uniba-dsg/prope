package prope.metrics.adaptability.elements;

class DetectionOnlyElements extends ElementsCollection {

	private EventElements events;

	public DetectionOnlyElements() {
		events = new EventElements();
		buildSequenceFlow();
		buildThrowLinkEvent();
		buildCatchLinkEvent();
		buildTask();
		buildNoneEndEvent();
		buildNoneStartEvent();
	}

	private void buildSequenceFlow() {
		AdaptableElement sequenceFlow = new AdaptableElement("sequenceFlow",
				true);
		sequenceFlow.setLocatorExpression("//*[local-name() = 'sequenceFlow']");
		sequenceFlow.setDocumentation("A sequenceFlow is a basic language element that cannot be adapted");
		add(sequenceFlow);
	}

	private void buildThrowLinkEvent() {
		AdaptableElement linkEvent = new AdaptableElement("linkThrowEvent",
				true);
		linkEvent.setLocatorExpression(events.buildEventXPathExpression(
				"intermediateThrowEvent", "link"));
		linkEvent.setDocumentation("Link events are only relevant to visualization and therefore not relevant for adaptation");
		add(linkEvent);
	}

	private void buildCatchLinkEvent() {
		AdaptableElement linkEvent = new AdaptableElement("linkCatchEvent",
				true);
		linkEvent.setLocatorExpression(events.buildEventXPathExpression(
				"intermediateCatchEvent", "link"));
		linkEvent.setDocumentation("Link events are only relevant to visualization and therefore not relevant for adaptation");
		add(linkEvent);
	}

	private void buildTask() {
		AdaptableElement task = new AdaptableElement("task", true);
		task.setLocatorExpression("//*[local-name() = 'task' or local-name() = 'globalTask']");
		task.setDocumentation("A plain task is a kind of wildcard element for an unspecified task and irrelevant to process execution");
		add(task);
	}

	private void buildNoneEndEvent() {
		AdaptableElement noneEndEvent = new AdaptableElement("noneEndEvent");
		noneEndEvent
				.setLocatorExpression("//*[local-name() = 'endEvent' and not(child::*[contains(local-name(),'ventDefinition')])]");
		noneEndEvent
				.setDocumentation("A noneEndEvent can be adapted to any other endEvent that represents ordered termination");
		noneEndEvent.addAdaption("messageEndEvent");
		noneEndEvent.addAdaption("signalEndEvent");
		noneEndEvent.addAdaption("multipleEndEvent");
		add(noneEndEvent);
	}

	private void buildNoneStartEvent() {
		AdaptableElement noneStartEvent = new AdaptableElement("noneStartEvent");
		noneStartEvent
				.setLocatorExpression("/*[local-name() = 'process']/*[local-name() = 'startEvent' and not(/*[contains(local-name(),'ventDefinition')])]");
		noneStartEvent
				.setDocumentation("A noneStartEvent can be adapted to another start event that represents normal process instantiation");

		noneStartEvent.addAdaption("messageStartEvent");
		noneStartEvent.addAdaption("conditionalStartEvent");
		noneStartEvent.addAdaption("signalStartEvent");
		noneStartEvent.addAdaption("multipleStartEvent");
		noneStartEvent.addAdaption("parallelMultipleStartEvent");
		add(noneStartEvent);
	}

}
