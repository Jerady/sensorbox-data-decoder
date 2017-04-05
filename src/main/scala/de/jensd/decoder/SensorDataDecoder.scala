package de.jensd.decoder

import java.io.ByteArrayInputStream

import com.google.protobuf.InvalidProtocolBufferException
import de.jensd.addon.decoder.{AbstractPayloadDecoder, PayloadDecoder}
import de.jensd.proto.sensorboxdata.{SensorBoxData, SensorData}

object SensorDataDecoder{

  def toSensorBoxDataHeaderString(sensorBoxData:SensorBoxData):String = {
    val out =
      s"""|id=${sensorBoxData.id}
         |name=${sensorBoxData.name}
         |description=${sensorBoxData.description}
         |
         |""".stripMargin
    out
  }

  def toSensorDataString(sensorData:SensorData):String = {
    val out =
      s"""|id=${sensorData.id}
         |name=${sensorData.name}
         |description=${sensorData.description}
         |time=${sensorData.time}
         |value=${sensorData.value}
         |unit=${sensorData.unit}
         |
         |""".stripMargin
    out
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
