#!/bin/bash

BASEDIR="$(dirname "$(dirname "$0")")"
SRC_DIR="$BASEDIR/lib/src"

CONTENT="library turms_client_dart;\n"
FILES=$(find "$SRC_DIR" -type f -name "*.dart" -printf "src/%P\n")
COUNT=$(echo "$FILES" | wc -l)
INDEX=0
for FILE in $FILES; do
    ((INDEX++))
    if echo "$FILE" | grep -q -E '.pbjson.|.pbenum.'; then
        echo "($INDEX/$COUNT) excluded: $FILE"
        continue
    fi
    echo "($INDEX/$COUNT) included: $FILE"
    SHOW=$(echo "$FILE" | sed "s/\//\n/g" | grep "\.dart" | sed "s/\./\n/g" | head -1 | sed -r 's/(^|_)([a-z])/\U\2/g')
    CONTENT="${CONTENT}\nexport '$FILE' show $SHOW;"
done
echo -e "$CONTENT" > "$BASEDIR/lib/turms_client.dart"
echo "Done"