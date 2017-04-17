package com.kumarvikas1.scala.core.domain.module.search

import com.kumarvikas1.scala.CoreLocators._
import org.openqa.selenium.By

/**
  * Created by vikakumar on 1/9/17.
  */
trait SearchHeaderModule {

  private val by: By = By.cssSelector("div.summary h1")

  def headerContains(destination: String) = {
    assert(by.waitForElementToDisplay.getText.contains(destination))
  }

}
