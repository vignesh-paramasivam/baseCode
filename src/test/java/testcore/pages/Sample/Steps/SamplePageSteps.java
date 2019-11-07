package testcore.pages.Sample.Steps;

import agent.IAgent;
import central.Configuration;
import testcore.pages.Sample.SamplePage;

import java.util.Map;

public class SamplePageSteps extends SamplePage {


	public SamplePageSteps(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
		assertPageLoad();
	}

	@Override
	public String pageName() {
		return SamplePageSteps.class.getSimpleName();
	}

}
