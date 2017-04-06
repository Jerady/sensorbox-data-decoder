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
package de.jensd.proto.publish


import de.jensd.proto.util.FakeData
import org.eclipse.paho.client.mqttv3.{MqttClient, MqttConnectOptions, MqttMessage}
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import de.jensd.proto.util.ProtoUtil._

object Publish extends App {

  val brokerIP = "localhost"
  val brokerURL = s"tcp://${brokerIP}:1883"
  val clientId = "demo-client-1234"
  val persistence = new MemoryPersistence()

  val mqttClient = new MqttClient(brokerURL, clientId, persistence)
  val mqttConnectOptions = new MqttConnectOptions()

  val topic = "demo/demo"
  val sensorBoxData = FakeData.fakeSensorBoxData

  val message = new MqttMessage(toPayload(sensorBoxData));

  mqttClient.connect(mqttConnectOptions)

  if (mqttClient.isConnected) {
    println(s"Connected to ${brokerURL}")

    for (i <- 1 to 10) {
      mqttClient.publish(topic, message);
      System.out.println(s"Message #${i} published");
      Thread.sleep(1000)
    }

    mqttClient.disconnect();
    System.out.println("Disconnected");
  }

}