package de.jensd.proto

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import de.jensd.proto.sensorboxdata._
import de.jensd.proto.ProtoUtil._
import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by jens on 21.03.17.
  */
class SensorBoxDataProtoTestSpec extends FlatSpec with Matchers{

  "A SensorBoxData" should "be serialized/deserialized without issues" in {
    val sensorBoxData = fakeSensorBoxData
    val output = new ByteArrayOutputStream()
    sensorBoxData.writeTo(output)
    sensorBoxData shouldEqual SensorBoxData.parseFrom(
      new ByteArrayInputStream(output.toByteArray)
    )
  }

  "An SensorBoxData-payload" should "be deserialized" in {
    val sensorBoxData = fakeSensorBoxData
    sensorBoxData shouldEqual SensorBoxData.parseFrom(
      toPayload(sensorBoxData))
  }


  private def fakeSensorBoxData =
    SensorBoxData(
      id = "1",
      name = "living-room",
      description = "The sensorbox of the living-room",
      sensorData = Seq(fakeTemperatureSensorData, fakeHumiditySensorData)
    )

  private def fakeTemperatureSensorData =
    SensorData(
      id = "11",
      name = "Temperature",
      description = "Temperature of the living-room",
      time = "2017-03-03T18:29:43",
      value = "21.3",
      unit = "Celsius"
    )
  private def fakeHumiditySensorData =
    SensorData(
      id = "11",
      name = "Humidity",
      description = "Humidity of the living-room",
      time = "2017-03-03T18:29:43",
      value = "55.3",
      unit = "%"
    )
}
