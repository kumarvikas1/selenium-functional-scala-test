package com.kumarvikas1.scala.core.test

import java.io.File

import com.kumarvikas1.scala.core.driver.DriverManager
import org.apache.commons.io.FileUtils
import org.openqa.selenium.{OutputType, TakesScreenshot}
import org.scalatest.{BeforeAndAfterEach, FeatureSpec, GivenWhenThen}

/**
  * Created by vikakumar on 12/5/16.
  */
class BaseTest extends FeatureSpec with BeforeAndAfterEach with GivenWhenThen {


  override def withFixture(test: NoArgTest) = {
    val outcome = super.withFixture(test)
    if (outcome.isFailed) {
      takeScreenshot(test)
    }
    DriverManager.remove()
    outcome
  }

  override protected def beforeEach(): Unit = super.beforeEach()

  def takeScreenshot(test: NoArgTest): Unit = {
    val scrFile = DriverManager.get().asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.FILE)
    val name = "./../screenshots/%s.png".format(test.name.replaceAll(" ", "_").split(":")(1))
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/target/screenshots/" + test.name.replaceAll(" ", "_").split(":")(1) + ".png"));
    TestResultInventory.addAttribute(test.name, List[String](name))
  }
}