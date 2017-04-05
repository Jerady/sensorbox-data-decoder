package de.jensd.demo

import de.jensd.proto.sensorboxdata.{SensorBoxData, SensorData}

/**
  * Created by jens on 27.03.17.
  */
object FakeData {
  def fakeSensorBoxData =
    SensorBoxData(
      id = "1",
      name = "living-room",
      description = "The sensorbox of the living-room",
      sensorData = Seq(fakeTemperatureSensorData, fakeHumiditySensorData)
    )

  def fakeTemperatureSensorData =
    SensorData(
      id = "11",
      name = "Temperature",
      description = "Temperature of the living-room",
      time = 1491409210061l,
      value = 21.3,
      unit = "Celsius"
    )

  def fakeHumiditySensorData =
    SensorData(
      id = "11",
      name = "Humidity",
      description = "Humidity of the living-room",
      time = 1491409210061l,
      value = 55.3,
      unit = "%"
    )
}
