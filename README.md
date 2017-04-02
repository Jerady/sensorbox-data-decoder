# SensorBox Data Decoder

SensorBox Data Decoder implementation dedicated to be uses as custom payload decoder add-on in
[MQTT.fx](http://mqttfx.org).

See also: [addon-commons](https://github.com/Jerady/addon-commons) and [mqttfx-payload-decoders](https://github.com/Jerady/mqttfx-payload-decoders).

## Proto Buffers

The SensorBox Data Decode decodes Proto Buffers encoded payload based on `proto/sensorboxdata.proto`
```
syntax = "proto3";
package sensorboxdata;

option java_package = "de.jensd.proto";
option java_outer_classname = "SensorBoxData";

message SensorData {
    string id = 1;
    string name = 2;
    string description = 3;
    string time  = 4;
    string value = 5;
    string unit = 6;
}

message SensorBoxData {
    string id = 1;
    string name = 2;
    string description = 3;
    repeated SensorData sensorData = 4;
}
```

This project includes the [sbt-protoc](https://github.com/thesamet/sbt-protoc) plugin.
The proto case classes are created at `../target/scala-2.11/src_managed/main`.


## Create addon package

`sbt assembly`

Copy the package from

`../target/scala-2.11/sensorbox-data-decoder-assembly-1.0.jar`

into the addon directory of MQTT.fx

| OS        | Add-on location          |
|---|---|
|**Mac OSX**|`[USER_HOME]/Library/Application Support/MQTT-FX/addons`|
|**Windows**|`[USER_HOME]\AppData\Local\MQTT-FX\addons`|
|**Linux**|`[USER_HOME]/MQTT-FX/addons`|

