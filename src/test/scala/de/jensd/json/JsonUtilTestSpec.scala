package de.jensd.json

import de.jensd.json.demo.SensorDataDemo.{SensorBoxData, SensorData}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by jens on 21.03.17.
  */
class JsonUtilTestSpec extends FlatSpec with Matchers{

  "JsonUtil.toJson" should "have length of 359" in {
      val sensorDataJson = JsonUtil.toJson(fakeSensorBoxData)
      sensorDataJson.length().should(be(359))
  }

  "JsonUtil.fromJson" should "have 21.3 as temperature value" in {
    val sensorDataFromJson = JsonUtil.fromJson[SensorBoxData](fakeSensorBoxDataJson)
    sensorDataFromJson.data.length.should(be(2))
    val temperatureData = sensorDataFromJson.data(0)
    temperatureData.value.should(be("21.3"))
  }

  private def fakeSensorBoxDataJson =
   """{"id":"1","name":"living-room","description":"The sensorbox of the living-room","data":[{"id":"11","name":"Temperature","description":"Temperature","time":"2017-03-03T18:29:43","value":"21.3","unit":"Celsius"},{"id":"12","name":"Humidity","description":"Humidity","time":"2017-03-03T18:29:43","value":"55.3","unit":"%"}]}"""

  private def fakeSensorBoxData =
    SensorBoxData(
      id = "1",
      name = "living-room",
      description = "The sensorbox of the living-room",
      data = Seq(fakeTemperatureSensorData, fakeHumiditySensorData)
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