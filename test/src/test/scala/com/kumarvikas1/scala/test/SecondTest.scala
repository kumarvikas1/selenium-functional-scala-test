package com.kumarvikas1.scala.test

import com.kumarvikas1.scala.core.domain.page.HomePage
import com.kumarvikas1.scala.core.test.BaseTest;

/**
  * Created by vikakumar on 11/20/16.
  */
class SecondTest extends BaseTest {
  var home: HomePage = _;

  feature("Hotels.com Home page is displayed") {
    info("As a User")
    info("I should be able to load hotels.com home page")

  }

  scenario("Hotels.com Home Page is displayed") {
    Given("Home Page is deployed")
    home = new HomePage;
    When("Hotels.com Home page is opened")
    home.open
    Then("It is opened")
    assert(home.is_shown)
  }

  scenario("Hotels.com Home Page is displayed again") {
    Given("Home Page is deployed")
    home = new HomePage;
    When("Hotels.com Home page is opened")
    home.open
    Then("It is opened")
    assert(home.is_shown)
  }


}