#!/bin/bash

BASEDIR="$(dirname "$0")"
PROTO_DIR="$BASEDIR/Protos"
SWIFTPROTO_DIR="$BASEDIR/Sources/TurmsClient/Model/Proto"
echo "$BASEDIR"
echo "$PROTO_DIR"
FILES=$(find "$PROTO_DIR" -type f -name "*.proto")
for FILE in $FILES; do
    echo "$FILE"
    protoc -I="$PROTO_DIR" --swift_opt=Visibility=Public --swift_out="$SWIFTPROTO_DIR" "$FILE"
done
echo "Done"
