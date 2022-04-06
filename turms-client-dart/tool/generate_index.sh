#!/bin/bash

BASEDIR="$(dirname "$(dirname $0)")"
SRC_DIR="$BASEDIR/lib/src"

INDEX="library turms_client_dart;\n"
FILES=$(find $SRC_DIR -type f -name "*.dart" -printf "src/%P\n")
for FILE in $FILES; do
    if echo "$FILE" | grep -q -E '.pbjson.|.pbenum.'; then
        continue
    fi
    SHOW=$(echo $FILE | sed "s/\//\n/g" | grep "\.dart" | sed "s/\./\n/g" | head -1 | sed -r 's/(^|_)([a-z])/\U\2/g')
    INDEX="$INDEX\nexport '$FILE' show $SHOW;"
done
echo -e $INDEX > "$BASEDIR/lib/turms_client.dart"