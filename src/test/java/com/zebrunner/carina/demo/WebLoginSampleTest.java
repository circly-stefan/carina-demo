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
package com.zebrunner.carina.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.zebrunner.carina.demo.gui.pages.common.WebLoginPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;

/**
 * This sample shows how to create a Web login test.
 *
 * @author qpsdemo
 */
public class WebLoginSampleTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = { "web", "login", "acceptance" })
    public void testValidLogin() {
        // Open login page
        WebLoginPageBase loginPage = initPage(getDriver(), WebLoginPageBase.class);
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform login with valid credentials
        String username = R.TESTDATA.get("login_username");
        String password = R.TESTDATA.get("login_password");
        loginPage.login(username, password);

        // Verify successful login
        Assert.assertTrue(loginPage.isLoggedIn(), "User is not logged in");
        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged into a secure area!"),
                "Flash message does not indicate successful login");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P2)
    @TestLabel(name = "feature", value = { "web", "login", "regression" })
    public void testInvalidLogin() {
        // Open login page
        WebLoginPageBase loginPage = initPage(getDriver(), WebLoginPageBase.class);
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform login with invalid credentials
        loginPage.login("invaliduser", "invalidpass");

        // Verify login failure
        Assert.assertFalse(loginPage.isLoggedIn(), "User should not be logged in with invalid credentials");
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"),
                "Flash message does not indicate login failure");
    }

}
