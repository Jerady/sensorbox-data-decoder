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
package de.jensd.proto

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import de.jensd.proto.sensorboxdata._
import de.jensd.proto.util.FakeData
import de.jensd.proto.util.ProtoUtil._
import org.scalatest.{FlatSpec, Matchers}

class SensorBoxDataProtoTestSpec extends FlatSpec with Matchers{

  "A SensorBoxData" should "be serialized/deserialized without issues" in {
    val sensorBoxData = FakeData.fakeSensorBoxData
    val output = new ByteArrayOutputStream()
    sensorBoxData.writeTo(output)
    sensorBoxData shouldEqual SensorBoxData.parseFrom(
      new ByteArrayInputStream(output.toByteArray)
    )
  }

  "An SensorBoxData-payload" should "be deserialized" in {
    val sensorBoxData = FakeData.fakeSensorBoxData
    sensorBoxData shouldEqual SensorBoxData.parseFrom(
      toPayload(sensorBoxData))
  }



}
