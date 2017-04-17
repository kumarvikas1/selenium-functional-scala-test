package com.kumarvikas1.scala.test

import com.kumarvikas1.scala.core.domain.page.{HomePage, SearchPage}
import com.kumarvikas1.scala.core.test.BaseTest
import org.scalatest.GivenWhenThen

/**
  * Created by vikakumar on 11/20/16.
  */
class FirstTest extends BaseTest with GivenWhenThen {
  var home: HomePage = _;
  var search: SearchPage = _;
  feature("Hotels.com Search is working") {
    info("As a User")
    info("I should be able to search for destination")

  }

  scenario("Search is Working") {
    Given("Home Page is deployed")
    home = new HomePage;
    When("Hotels.com Home page is opened")
    home.open
    And("User makes a search for a destination")
    home.searchDestination("London")
    Then("Search is shown for the destination")
    search = new SearchPage
    search.headerContains("vikas")
  }

}