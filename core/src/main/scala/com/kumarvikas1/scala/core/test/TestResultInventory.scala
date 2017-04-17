package com.kumarvikas1.scala.core.test

import com.google.common.base.Splitter
import com.google.common.collect.Iterables

import scala.collection.mutable.ListBuffer

/**
  * Created by vikakumar on 12/21/16.
  */
object TestResultInventory {

  val results = new ListBuffer[TestResult];
  var resultMap = Map[String, List[String]]()
  var infoMap = Map[String, String]()


  def addResult(testResult: TestResult) = {
    results += testResult;
  }

  def addAttribute(name: String, attr: List[String]) = {
    resultMap += (name -> attr)
  }

  def getAttribute(name: String) = {
    resultMap.get(name).get(0)
  }

  def addInfo(thread: String, info: String) = {
    val testname = Iterables.getLast(Splitter.on("-").splitToList(thread))
    if (infoMap.get(testname).isEmpty) {
      infoMap += (testname -> info)
    }
    else {
      val existinfo = infoMap.get(testname).get
      infoMap += (testname -> existinfo.concat("\n" + info))
    }
  }

  def getResults: ListBuffer[TestResult] = {
    results
  }

  def getInfo(thread: String): String = {
    infoMap.get(Iterables.getLast(Splitter.on("-").splitToList(thread))).get
  }


}
