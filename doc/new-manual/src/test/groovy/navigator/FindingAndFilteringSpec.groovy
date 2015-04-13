/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package navigator

import geb.navigator.Navigator
import geb.test.GebSpecWithCallbackServer

class FindingAndFilteringSpec extends GebSpecWithCallbackServer {

    def setup() {
        responseHtml """
            <html>
                // tag::html[]
                <div class="a">
                    <p class="b">geb</p>
                </div>
                <div class="b">
                    <input type="text"/>
                </div>
                // end::html[]
            <html>
        """

        go()
    }

    private Navigator bParagraphUsingFind() {
        // tag::find[]
        $("div").find(".b")
        // end::find[]
    }

    private Navigator bParagraphUsingDollar() {
        // tag::dollar[]
        $("div").$(".b")
        // end::dollar[]
    }

    private Navigator bDivUsingFilter() {
        // tag::filter[]
        $("div").filter(".b")
        // end::filter[]
    }

    private Navigator bDivUsingNot() {
        // tag::not[]
        $(".b").not("p")
        // end::not[]
    }

    private Navigator aDivUsingHas() {
        // tag::a_has[]
        $("div").has("p")
        // end::a_has[]
    }

    private Navigator bDivUsingHas() {
        // tag::b_has[]
        $("div").has("input", type: "text")
        // end::b_has[]
    }

    private Navigator bDivUsingHasNot() {
        // tag::b_has_not[]
        $("div").hasNot("p")
        // end::b_has_not[]
    }

    private Navigator aDivUsingHasNot() {
        // tag::a_has_not[]
        $("div").hasNot("input", type: "text")
        // end::a_has_not[]
    }

    private Navigator divsUsingHasNot() {
        // tag::has_not[]
        $("div").hasNot("input", type: "submit")
        // end::has_not[]
    }

    def "selectors work as expected"() {
        expect:
        bParagraphUsingFind().allElements() == $("p.b").allElements()
        bParagraphUsingDollar().allElements() == $("p.b").allElements()
        bDivUsingFilter().allElements() == $("div.b").allElements()
        bDivUsingNot().allElements() == $("div.b").allElements()
        aDivUsingHas().allElements() == $("div.a").allElements()
        bDivUsingHas().allElements() == $("div.b").allElements()
        bDivUsingHasNot().allElements() == $("div.b").allElements()
        aDivUsingHasNot().allElements() == $("div.a").allElements()
        divsUsingHasNot().allElements() == $("div").allElements()
    }
}