package testcore.pages.Sample;

import agent.IAgent;
import central.Configuration;
import org.apache.commons.lang3.NotImplementedException;
import testcore.pages.BasePage;
import testcore.pages.desktop.Sample.DesktopSamplePage;

import java.util.Map;

public class SamplePage extends BasePage {


	public SamplePage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
		assertPageLoad();
	}

	public SamplePage createInstance() throws Exception{
		logger.info(String.format("started with the create instance"));
		logger.info(getPlatform().toString());
		SamplePage derivedSamplePage;
		switch(getPlatform()){
			case DESKTOP_WEB:
				derivedSamplePage = new DesktopSamplePage(getConfig(),getAgent(),getTestData());
				break;
			default:
				throw new NotImplementedException("Invalid platform - please check the platform argument: " + getPlatform().toString()) ;
		}
		return derivedSamplePage;
	}


	@Override
	public String pageName() {
		return SamplePage.class.getSimpleName();
	}
	
}
