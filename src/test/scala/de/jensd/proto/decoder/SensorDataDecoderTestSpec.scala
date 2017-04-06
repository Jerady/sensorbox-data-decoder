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

import java.io.ByteArrayOutputStream

import de.jensd.proto.util.FakeData
import org.scalatest.{FlatSpec, Matchers}


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
