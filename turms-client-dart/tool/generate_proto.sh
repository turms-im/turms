#!/bin/bash

BASEDIR="$(dirname "$(dirname $0)")"
PROTO_DIR="$BASEDIR/proto"
DARTPROTO_DIR="$BASEDIR/lib/src/model"
echo $BASEDIR
echo $PROTO_DIR
FILES=$(find $PROTO_DIR -type f -name "*.proto")
for proto in $FILES; do
    echo $proto
    protoc -I=$PROTO_DIR --dart_out=$DARTPROTO_DIR $proto
done
echo "Done"
