package com.kumarvikas1.scala.core.report

import com.google.common.base.Strings
import com.kumarvikas1.scala.core.test.{TestResultInventory, TestStatus}
import com.relevantcodes.extentreports.{ExtentReports, ExtentTest, LogStatus}

/**
  * Created by vikakumar on 12/20/16.
  */
object BaseReporter {
  private val LINEBREAK: String = "\n"
  private val HTML_LINEBREAK: String = "<br>"
  private val TAB: String = "\t"
  private val HTML_TAB: String = Strings.repeat("&nbsp;", 3)

  val report: ExtentReports = new ExtentReports("target/extent-reports/index.html");
  private val EXTENT_SCREENSHOT_LOG: String = "Snapshot :<br><img class=\"report-img\" data-featherlight=" + "\"%s\" src=\"%s\""

  val status_map = Map("Succeeded" -> TestStatus(LogStatus.PASS, "passed"), "Failed" -> TestStatus(LogStatus.FAIL, "failed"))

  def createReport() = {
    var logger: ExtentTest = null;
    TestResultInventory.getResults.foreach(f => {
      logger = report.startTest(f.name);
      val status = status_map.get(f.result)
      logger.log(status.get.status, "Result", status.get.text)
      if (Option.apply(f.feature).isDefined) {
        logger.log(LogStatus.INFO, "Features", "<b>" + (TestResultInventory.getInfo(f.thread) + "\n" + f.feature).replaceAll(LINEBREAK, HTML_LINEBREAK).
          replaceAll(TAB, HTML_TAB) + "</b>")
      }
      if (Option.apply(f.stack).isDefined) {
        logger.log(LogStatus.INFO, "Screenshots", EXTENT_SCREENSHOT_LOG.format(TestResultInventory.getAttribute(f.name), TestResultInventory.getAttribute(f.name)))
        logger.log(LogStatus.INFO, "Errors", "<b>" + f.stack + "</b>")
      }

    })

    report.endTest(logger);
    report.flush();
  }


}
