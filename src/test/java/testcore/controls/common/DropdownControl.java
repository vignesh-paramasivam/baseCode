package testcore.controls.common;

import org.openqa.selenium.WebElement;
import control.WebControl;
import page.IPage;

public class DropdownControl extends WebControl {

	public DropdownControl(String name, IPage page, WebElement element) {
		super(name, page, element);
	}

	@Override
	public void enterValue(String value) throws Exception {
		selectDropDownByValue(value);
	}

}

