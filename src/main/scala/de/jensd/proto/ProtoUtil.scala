package de.jensd.proto

import java.io.ByteArrayOutputStream

import de.jensd.proto.sensorboxdata.SensorBoxData

/**
  * Created by jens on 21.03.17.
  */
object ProtoUtil {

  def toPayload(a: SensorBoxData): Array[Byte] = {
    val output = new ByteArrayOutputStream()
    a.writeTo(output)
    output.toByteArray
  }
}
