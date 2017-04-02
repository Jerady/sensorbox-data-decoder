package de.jensd.demo

import de.jensd.decoder
import de.jensd.decoder.Test
import org.eclipse.paho.client.mqttv3.{MqttClient, MqttConnectOptions, MqttMessage}
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import de.jensd.proto.ProtoUtil._
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test

/**
  * Created by Jens Deters on 24.03.17.
  */
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