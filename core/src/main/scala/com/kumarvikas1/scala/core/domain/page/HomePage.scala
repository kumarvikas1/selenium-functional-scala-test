package com.kumarvikas1.scala.core.domain.page

import com.kumarvikas1.scala.CoreLocators._
import com.kumarvikas1.scala.core.domain.BasePage
import com.kumarvikas1.scala.core.domain.module.SearchModule
import org.openqa.selenium.By

/**
  * Created by vikakumar on 12/5/16.
  */
class HomePage extends BasePage with SearchModule {
  private val search: By = By.id("qf-0q-destination");

  def is_shown: Boolean = {
    return search.isDisplayed
  }


  def open: HomePage = {
    driver().get("http://uk.hotels.com")
    return this;
  }
}
