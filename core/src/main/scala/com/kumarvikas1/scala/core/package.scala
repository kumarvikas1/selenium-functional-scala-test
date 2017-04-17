package com.kumarvikas1.scala

import com.google.common.base.Predicate
import com.kumarvikas1.scala.core.driver.DriverManager
import org.openqa.selenium._
import org.openqa.selenium.support.ui.WebDriverWait

/**
  * Created by vikakumar on 1/2/17.
  */
package object CoreLocators {

  implicit class WebElementUtils(by: By) {
    def isDisplayed: Boolean = {
      DriverManager.get().findElement(by).isDisplayed
    }

    def waitForElementToDisplay: WebElement = {
      new WebDriverWait(DriverManager.get(), 10).until(toPredicate((f) => isDisplayed))
      DriverManager.get().findElement(by)
    }

    def get: WebElement = {
      DriverManager.get().findElement(by)
    }

    def jsClick = {
      DriverManager.get().asInstanceOf[JavascriptExecutor].executeScript("arguments[0].click();", DriverManager.get().findElement(by))
    }


    implicit def toPredicate(function: WebDriver => Boolean): Predicate[WebDriver] = new Predicate[WebDriver]() {
      override def apply(arg: WebDriver): Boolean = function.apply(arg)
    }

  }

}
