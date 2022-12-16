#!/bin/bash

BASEDIR="$(dirname "$(dirname "$0")")"
PROTO_DIR="$BASEDIR/proto"
DARTPROTO_DIR="$BASEDIR/lib/src/model/proto"
echo "$BASEDIR"
echo "$PROTO_DIR"
FILES=$(find "$PROTO_DIR" -type f -name "*.proto")
COUNT=$(echo "$FILES" | wc -l)
INDEX=0
for FILE in $FILES; do
    ((INDEX++))
    echo "($INDEX/$COUNT) $FILE"
    protoc -I="$PROTO_DIR" --dart_out="$DARTPROTO_DIR" "$FILE"
done

find "$DARTPROTO_DIR" -type f -name "*.pbserver.dart" -delete

echo "Done"