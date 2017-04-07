/**
  * Copyright (c) 2017 Jens Deters http://www.jensd.de
  *
  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
  * use this file except in compliance with the License. You may obtain a copy of
  * the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  * License for the specific language governing permissions and limitations under
  * the License.
  *
  */
package de.jensd.proto.decoder

import java.io.ByteArrayInputStream
import java.time.{Instant, LocalDateTime, ZoneId}
import java.time.format.DateTimeFormatter

import com.google.protobuf.InvalidProtocolBufferException
import de.jensd.addon.decoder.AbstractPayloadDecoder
import de.jensd.proto.sensorboxdata.{SensorBoxData, SensorData}

object SensorDataDecoder{

  def toSensorBoxDataHeaderString(sensorBoxData:SensorBoxData):String = {
    val out =
      s"""|id............: ${sensorBoxData.id}
          |name..........: ${sensorBoxData.name}
          |description...: ${sensorBoxData.description}
          |
          |""".stripMargin
    out
  }

  def toSensorDataString(sensorData:SensorData):String = {
    val out =
      s"""|id............: ${sensorData.id}
          |name..........: ${sensorData.name}
          |description...: ${sensorData.description}
          |time..........: ${millisToDate(sensorData.time)}
          |value.........: ${sensorData.value}
          |unit..........: ${sensorData.unit}
          |
          |""".stripMargin
    out
  }

  def millisToDate(timeInMillis:Long):String = {
   val date =
      LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMillis), ZoneId.systemDefault())
    DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(date)
  }

  def invalidProtocolBufferMessage():String = {
    val out =
      s"""
         |*************************************************
         |*                                               *
         |*       NOT a SensorBoxData proto payload       *
         |*                                               *
         |*************************************************
       """.stripMargin
    out
  }
}


class SensorDataDecoder extends AbstractPayloadDecoder{

  idProperty().set("sensor_box_data_proto_decoder")
  nameProperty().set("SensorBoxData Protobuffer Decoder")
  versionProperty().set("1.0.0")
  descriptionProperty().set("Decodes payload from Protobuffer to formatted string values")

  override def decode(payload: Array[Byte]): String = {
    try{
    val sensorBoxData = SensorBoxData.parseFrom(new ByteArrayInputStream(payload))
    var data = SensorDataDecoder.toSensorBoxDataHeaderString(sensorBoxData)
    sensorBoxData.sensorData.foreach(data+=SensorDataDecoder.toSensorDataString(_))
    data
    }catch{
      case ex: InvalidProtocolBufferException => SensorDataDecoder.invalidProtocolBufferMessage
    }
  }


}
