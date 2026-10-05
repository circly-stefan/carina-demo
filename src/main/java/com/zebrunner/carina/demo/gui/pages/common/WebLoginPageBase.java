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
package com.zebrunner.carina.demo.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.demo.gui.components.FlashMessage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class WebLoginPageBase extends AbstractPage {

    public WebLoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract WebLoginPageBase typeUsername(String username);

    public abstract WebLoginPageBase typePassword(String password);

    public abstract SecureAreaPageBase clickLogin();

    public abstract FlashMessage getFlashMessage();

    public abstract boolean isLoggedIn();

    public abstract boolean isUsernameFieldEmpty();

    public abstract boolean isPasswordFieldEmpty();

    public abstract boolean isLoginButtonEnabled();

    /**
     * Fluent login method. Returns SecureAreaPageBase on successful login.
     * WARNING: On failed login, the page stays on the login form. initPage will wait for the
     * welcome-message UI marker and may time out. For negative-path tests, use typeUsername/typePassword
     * and clickLogin separately, then assert on getFlashMessage() instead.
     */
    public SecureAreaPageBase login(String username, String password) {
        typeUsername(username);
        typePassword(password);
        return clickLogin();
    }

}
