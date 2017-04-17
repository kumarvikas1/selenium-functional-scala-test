package com.kumarvikas1.scala.core.domain

import com.kumarvikas1.scala.core.driver.DriverManager
import org.openqa.selenium.WebDriver

/**
  * Created by vikakumar on 1/8/17.
  */
class BasePage {


  def driver(): WebDriver = {
    DriverManager.get();
  }

}
