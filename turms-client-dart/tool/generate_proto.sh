#!/bin/bash

BASEDIR="$(dirname "$(dirname $0)")"
PROTO_DIR="$BASEDIR/proto"
DARTPROTO_DIR="$BASEDIR/lib/src/model/proto"
echo $BASEDIR
echo $PROTO_DIR
FILES=$(find $PROTO_DIR -type f -name "*.proto")
for proto in $FILES; do
    echo $proto
    protoc -I=$PROTO_DIR --dart_out=$DARTPROTO_DIR $proto
done

find $DARTPROTO_DIR -type f -name "*.pbserver.dart" | xargs rm -f

echo "Done"
