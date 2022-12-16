#!/bin/bash

BASEDIR="$(dirname "$0")"
PROTO_DIR="$BASEDIR/Protos"
SWIFTPROTO_DIR="$BASEDIR/Sources/TurmsClient/Model/Proto"
echo "$BASEDIR"
echo "$PROTO_DIR"
FILES=$(find "$PROTO_DIR" -type f -name "*.proto")
COUNT=$(echo "$FILES" | wc -l)
INDEX=0
for FILE in $FILES; do
    ((INDEX++))
    echo "($INDEX/$COUNT) $FILE"
    protoc -I="$PROTO_DIR" --swift_opt=Visibility=Public --swift_out="$SWIFTPROTO_DIR" "$FILE"
done
echo "Done"