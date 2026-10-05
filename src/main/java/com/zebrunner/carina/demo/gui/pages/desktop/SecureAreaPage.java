/*******************************************************************************
 * Copyright 2020-2023 Zebrunner Inc (https://www.zebrunner.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.zebrunner.carina.demo.gui.pages.desktop;

import java.lang.invoke.MethodHandles;

import com.zebrunner.carina.demo.gui.pages.common.SecureAreaPageBase;
import com.zebrunner.carina.demo.gui.pages.common.WebLoginPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SecureAreaPageBase.class)
public class SecureAreaPage extends SecureAreaPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//div[@class='content']//h2")
    private ExtendedWebElement welcomeMessage;

    @FindBy(xpath = "//a[@href='/logout']")
    private ExtendedWebElement logoutLink;

    public SecureAreaPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(welcomeMessage);
    }

    @Override
    public String getWelcomeMessage() {
        LOGGER.info("getting welcome message");
        return welcomeMessage.getText();
    }

    @Override
    public WebLoginPageBase logout() {
        LOGGER.info("clicking logout link");
        logoutLink.click();
        return initPage(driver, WebLoginPageBase.class);
    }

    @Override
    public boolean isLogoutLinkPresent() {
        return logoutLink.isPresent();
    }

}
