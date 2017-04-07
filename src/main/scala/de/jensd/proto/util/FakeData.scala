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
package de.jensd.proto.util

import de.jensd.proto.sensorboxdata.{SensorBoxData, SensorData}

object FakeData {
  def fakeSensorBoxData =
    SensorBoxData(
      id = "lvgro-box-1",
      name = "living-room",
      description = "The sensorbox of the living-room",
      sensorData = Seq(fakeTemperatureSensorData, fakeHumiditySensorData)
    )

  def fakeTemperatureSensorData =
    SensorData(
      id = "lvgro-tmp-1",
      name = "Temperature",
      description = "Temperature of the living-room",
      time = 1491409210061l,
      value = 21.3,
      unit = "Celsius"
    )

  def fakeHumiditySensorData =
    SensorData(
      id = "lvgro-hum-1",
      name = "Humidity",
      description = "Humidity of the living-room",
      time = 1491409210061l,
      value = 55.3,
      unit = "%"
    )
}
