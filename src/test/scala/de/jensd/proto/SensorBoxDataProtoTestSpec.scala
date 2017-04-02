package de.jensd.proto

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import de.jensd.demo.FakeData
import de.jensd.proto.sensorboxdata._
import de.jensd.proto.ProtoUtil._
import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by jens on 21.03.17.
  */
class SensorBoxDataProtoTestSpec extends FlatSpec with Matchers{

  "A SensorBoxData" should "be serialized/deserialized without issues" in {
    val sensorBoxData = FakeData.fakeSensorBoxData
    val output = new ByteArrayOutputStream()
    sensorBoxData.writeTo(output)
    sensorBoxData shouldEqual SensorBoxData.parseFrom(
      new ByteArrayInputStream(output.toByteArray)
    )
  }

  "An SensorBoxData-payload" should "be deserialized" in {
    val sensorBoxData = FakeData.fakeSensorBoxData
    sensorBoxData shouldEqual SensorBoxData.parseFrom(
      toPayload(sensorBoxData))
  }



}
