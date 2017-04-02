package de.jensd.json.demo

import de.jensd.json.JsonUtil
import de.jensd.proto.sensorboxdata.{SensorBoxData, SensorData}

/**
  * Created by jens on 20.03.17.
  */
object SensorDataDemo extends App{

  case class SensorData(id:String, name:String, description:String, time:String, value:String, unit:String)
  case class SensorBoxData(id:String, name:String, description:String, data:Seq[SensorData])

  val temperatureData = new SensorData("11", "Temperature", "Temperature", "2017-03-03T18:29:43", "21.3", "Celsius")
  val humidityData = new SensorData("12", "Humidity", "Humidity", "2017-03-03T18:29:43", "55.3", "%")
  val sensorData = new SensorBoxData("1","living-room","The sensorbox of the living-room",Seq(temperatureData,humidityData))

  val sensorDataJson = JsonUtil.toJson(sensorData)

  println(sensorDataJson)

  val sensorDataFromJson = JsonUtil.fromJson[SensorBoxData](sensorDataJson)


  println(JsonUtil.toPrettyJson(sensorDataJson))




}
