# SensorBox Data Decoder



## Protobuffer with NanoBP for Arduino
https://github.com/nanopb/nanopb

Download binaries: 
https://jpa.kapsi.fi/nanopb/download/

cd /Users/jens/Downloads/nanopb-0.3.8-macosx-x86
generator-bin/protoc -I=/Users/jens/DevWork/SensorBoxDataDecoder/proto/ --nanopb_out=. /Users/jens/DevWork/SensorBoxDataDecoder/proto/sensorboxdata.proto

Generates:
sensorboxdata.pb.h
sensorboxdata.pb.c


## Prepare Arduino IDE

Close Arduino IDE
/Users/jens/Documents/Arduino/libraries

Create directory 
`NanoPB`

Copy
```
pb.h
pb_common.c
pb_common.h
pb_decode.c
pb_decode.h
pb_encode.c
pb_encode.h
```
