package de.jensd.decoder

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import com.google.protobuf.InvalidProtocolBufferException
import de.jensd.demo.FakeData
import de.jensd.proto.sensorboxdata.{SensorBoxData, SensorData}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by jens on 27.03.17.
  */


class SensorDataDecoderTestSpec extends FlatSpec with Matchers {

  "A decoded SensorBoxData string" should "be formatted as expected" in {
    val sensorBoxData = FakeData.fakeSensorBoxData
    val output = new ByteArrayOutputStream()
    sensorBoxData.writeTo(output)
    val sensorDataDecoder = new SensorDataDecoder
    val result = sensorDataDecoder.decode(output.toByteArray)
    result should === (expectedResultString)
  }


  "Decoding any other byte array" should "fail" in {
    val byteArray = Array[Byte](100.toByte, 101.toByte, 102.toByte, 103.toByte)
    val sensorDataDecoder = new SensorDataDecoder
    var result = sensorDataDecoder.decode(byteArray)
    result should === (SensorDataDecoder.invalidProtocolBufferMessage)
  }

  def expectedResultString =
    """id=1
      |name=living-room
      |description=The sensorbox of the living-room
      |
      |id=11
      |name=Temperature
      |description=Temperature of the living-room
      |time=1491409210061
      |value=21.3
      |unit=Celsius
      |
      |id=11
      |name=Humidity
      |description=Humidity of the living-room
      |time=1491409210061
      |value=55.3
      |unit=%
      |
      |""".stripMargin


}
