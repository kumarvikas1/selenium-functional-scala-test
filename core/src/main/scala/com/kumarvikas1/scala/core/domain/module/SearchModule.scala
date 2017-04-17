package com.kumarvikas1.scala.core.domain.module

import com.kumarvikas1.scala.CoreLocators._
import org.openqa.selenium.By

/**
  * Created by vikakumar on 1/8/17.
  */
trait SearchModule {

  private val search: By = By.id("qf-0q-destination")
  private val submit: By = By.cssSelector(".cta.cta-strong")

  def searchDestination(destination: String) = {
    search.waitForElementToDisplay.sendKeys(destination)
    submit.jsClick
  }

}
