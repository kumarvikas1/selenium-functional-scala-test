package com.kumarvikas1.scala.core.listeners

import java.util.function.Consumer

import com.google.common.base.{Strings, Throwables}
import com.kumarvikas1.scala.core.report.BaseReporter
import com.kumarvikas1.scala.core.test.{TestResult, TestResultInventory}
import org.scalatest.Reporter
import org.scalatest.events.{Event, InfoProvided, _}

/**
  * Created by vikakumar on 12/18/16.
  */
class BaseListeners extends Reporter {
  private val LINEBREAK: String = "\n"
  private val HTML_LINEBREAK: String = "<br>"
  private val TAB: String = "\t"
  private val HTML_TAB: String = Strings.repeat("&nbsp;", 3)

  var eventMap = Map[String, Consumer[Event]]()


  override def apply(event: Event): Unit = {
    initEvents
    val key: String = event.getClass.getSimpleName
    eventMap.get(key).foreach(f => f.accept(event))
    logInfo(event)
  }


  def logInfo(event: Event): Unit = {
    if (event.isInstanceOf[InfoProvided]) {
      TestResultInventory.addInfo(event.threadName, event.asInstanceOf[InfoProvided].productElement(1).toString)
    }
  }

  def logTestFeatures(event: Event): String = {
    event.getClass.getSimpleName match {
      case "TestSucceeded" =>
        event.asInstanceOf[TestSucceeded].recordedEvents.toStream.map(f => f.productElement(1)).toList.mkString("\n")
      case "TestFailed" =>
        event.asInstanceOf[TestFailed].recordedEvents.toStream.map(f => f.productElement(1)).toList.mkString("\n")
      case _ => null
    }
  }

  def initEvents = {
    eventMap += ("TestSucceeded" -> toConsumer[Event]((f) => succeededEvent(f)))
    eventMap += ("TestFailed" -> toConsumer[Event]((f) => failedEvent(f)))
    eventMap += ("RunCompleted" -> toConsumer[Event]((f) => ReportingEvent()))
  }

  def succeededEvent(event: Event) = {
    val test = event.asInstanceOf[TestSucceeded]
    TestResultInventory.addResult(TestResult(test.testName, "Succeeded", logTestFeatures(event), null, event.threadName))
  }

  def failedEvent(event: Event) = {
    val test = event.asInstanceOf[TestFailed]
    TestResultInventory.addResult(TestResult(test.testName, "Failed", logTestFeatures(event), Throwables.getStackTraceAsString(test.throwable.get).replaceAll(LINEBREAK, HTML_LINEBREAK).
      replaceAll(TAB, HTML_TAB), event.threadName))
  }

  def ReportingEvent() = {
    BaseReporter.createReport()
  }

  def toConsumer[A](function: A => Unit): Consumer[A] = new Consumer[A]() {
    override def accept(arg: A): Unit = function.apply(arg)
  }

}


