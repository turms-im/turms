/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package integration.im.turms.server.common.domain.observation.access.admin.controller;

import java.nio.file.Path;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import helper.SpringAwareIntegrationTest;
import org.junit.jupiter.api.RepeatedTest;

import im.turms.server.common.access.admin.web.HttpRequestDispatcher;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * @author James Chen
 */
class OpenApiControllerIT extends SpringAwareIntegrationTest {

    @RepeatedTest(2)
    void test() {
        HttpRequestDispatcher dispatcher = context.getBean(HttpRequestDispatcher.class);
        int port = dispatcher.port();
        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium()
                    .launch()) {
                Page page = browser.newPage();
                page.navigate("http://localhost:"
                        + port
                        + "/openapi/ui");
                try {
                    testMainPage(page);
                } finally {
                    Page.ScreenshotOptions screenshotOptions =
                            new Page.ScreenshotOptions().setPath(Path.of("openapi.png"));
                    page.screenshot(screenshotOptions);
                }
            }
        }
    }

    private void testMainPage(Page page) {
        Locator containerSector = page.locator(".swagger-ui.swagger-container");
        assertThat(containerSector.first()).isVisible();

        Locator apiDocsLocator = page.locator(".main .link");
        assertThat(apiDocsLocator).isVisible();

        Locator opblockTagSectionLocator = page.locator(".opblock-tag-section");
        Locator firstOpblockTagSectionLocator = opblockTagSectionLocator.first();
        assertThat(firstOpblockTagSectionLocator).isVisible();
    }

}