package de.jensd.decoder

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

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


  def expectedResultString =
    """id=1
      |name=living-room
      |description=The sensorbox of the living-room
      |
      |id=11
      |name=Temperature
      |description=Temperature of the living-room
      |description=2017-03-03T18:29:43
      |description=21.3
      |description=Celsius
      |
      |id=11
      |name=Humidity
      |description=Humidity of the living-room
      |description=2017-03-03T18:29:43
      |description=55.3
      |description=%
      |
      |""".stripMargin


}
