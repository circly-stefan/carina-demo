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

import com.zebrunner.carina.demo.gui.pages.common.WebLoginPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = WebLoginPageBase.class)
public class WebLoginPage extends WebLoginPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(id = "username")
    private ExtendedWebElement usernameInput;

    @FindBy(id = "password")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private ExtendedWebElement loginButton;

    @FindBy(id = "flash")
    private ExtendedWebElement flashMessage;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private ExtendedWebElement logoutLink;

    public WebLoginPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(loginButton);
    }

    @Override
    public void typeUsername(String username) {
        LOGGER.info("typing username: " + username);
        usernameInput.type(username);
    }

    @Override
    public void typePassword(String password) {
        LOGGER.info("typing password");
        passwordInput.type(password);
    }

    @Override
    public void clickLogin() {
        LOGGER.info("clicking login button");
        loginButton.click();
    }

    @Override
    public String getFlashMessage() {
        return flashMessage.getText();
    }

    @Override
    public boolean isLoggedIn() {
        return logoutLink.isPresent();
    }

}
