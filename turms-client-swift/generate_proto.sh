#!/bin/bash

BASEDIR="$(dirname $0)"
PROTO_DIR="$BASEDIR/Protos"
SWIFTPROTO_DIR="$BASEDIR/Sources/TurmsClient/Model"
echo $BASEDIR
echo $PROTO_DIR
FILES=$(find $PROTO_DIR -type f -name "*.proto")
for proto in $FILES; do
    echo $proto
    protoc -I=$PROTO_DIR --swift_opt=Visibility=Public --swift_out=$SWIFTPROTO_DIR $proto
done
echo "Done"
