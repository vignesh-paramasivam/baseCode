package testcore.pages;

import agent.IAgent;
import central.Configuration;
import testcore.pages.Sample.Steps.SamplePageSteps;
import java.util.Map;


public class AllPages extends BasePage {


	public AllPages(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
		assertPageLoad();
	}

	@Override
	public String pageName() {
		return AllPages.class.getSimpleName();
	}


	public SamplePageSteps onSamplePage() throws Exception {
		return new SamplePageSteps(getConfig(), getAgent(), getTestData());
	}

}
