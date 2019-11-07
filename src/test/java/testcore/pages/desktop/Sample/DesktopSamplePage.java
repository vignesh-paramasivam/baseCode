package testcore.pages.desktop.Sample;

import agent.IAgent;
import central.Configuration;
import testcore.pages.Sample.SamplePage;
import java.util.Map;


public class DesktopSamplePage extends SamplePage {

	public DesktopSamplePage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
		assertPageLoad();
	}

	@Override
	public String pageName() {
		return DesktopSamplePage.class.getSimpleName();
	}
}
