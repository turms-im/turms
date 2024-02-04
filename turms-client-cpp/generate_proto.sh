#!/bin/bash

BASEDIR="$(dirname "$0")"
IN_PROTO_DIR="$BASEDIR/proto"
OUT_PROTO_DIR="$BASEDIR/src/turms/client/model/proto"
mkdir -p "$OUT_PROTO_DIR"
echo "$BASEDIR"
echo "$IN_PROTO_DIR"
FILES=$(find "$IN_PROTO_DIR" -type f -name "*.proto")
COUNT=$(echo "$FILES" | wc -l)
INDEX=0
OPTION_OPTIMIZE_FOR="option optimize_for = LITE_RUNTIME;"
for FILE in $FILES; do
    ((INDEX++))
    echo "($INDEX/$COUNT) $FILE"
    sed -zi 's/package im.turms.proto;/package turms.client.model.proto;/' "$FILE"

    if ! grep -q "$OPTION_OPTIMIZE_FOR" "$FILE"; then
        PACKAGE_LINE=$(grep -n "^package " "$FILE" | cut -d':' -f1)
        LINE_TO_INSERT=$((PACKAGE_LINE + 1))
        sed -i "${LINE_TO_INSERT}i $OPTION_OPTIMIZE_FOR\n" "$FILE"
    fi

    protoc -I="$IN_PROTO_DIR" --cpp_out="$OUT_PROTO_DIR" "$FILE"
done

find "$OUT_PROTO_DIR" -type f -name "*.cc" | while read -r FILE; do
    DEST_FILE="${FILE%.cc}.cpp"
    mv "$FILE" "$DEST_FILE"
    sed -i 's/^#include "\(.*\.pb\.h\)"$/#include "turms\/client\/model\/proto\/\1"/g' "$DEST_FILE"
done

find "$OUT_PROTO_DIR" -type f -name "*.h" | while read -r FILE; do
    RELATIVE_PATH="${FILE#"$OUT_PROTO_DIR"/}"
    DEST_FILE="./include/turms/client/model/proto/$RELATIVE_PATH"
    mkdir -p "$(dirname "$DEST_FILE")"
    mv "$FILE" "$DEST_FILE"
    sed -i 's/^#include "\(.*\.pb\.h\)"$/#include "turms\/client\/model\/proto\/\1"/g' "$DEST_FILE"
done
echo "Done"