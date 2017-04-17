package com.kumarvikas1.scala.core.driver

import java.net.URL

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.{DesiredCapabilities, RemoteWebDriver}

/**
  * Created by vikakumar on 12/4/16.
  */
object DriverManager {
  var registry = new ThreadLocal[WebDriver];

  def get(): WebDriver = {
    def toOption(value: WebDriver): Option[WebDriver] = Option(value);
    return toOption(registry.get()).orElse(create()).get;
  }

  def create(): Option[WebDriver] = {
    val driver = new RemoteWebDriver(new URL("http://localhost:8886/wd/hub"), DesiredCapabilities.chrome());
    registry.set(driver);
    return Some(driver);
  }


  def remove(): Unit = {
    registry.get().quit();
    registry.remove();
  }

}
