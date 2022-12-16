#!/bin/bash

BASEDIR="$(dirname "$(dirname "$0")")"
SRC_DIR="$BASEDIR/lib/src"

FILES=$(find "$SRC_DIR" -type f -name "*.dart" -printf "src/%P\n")
COUNT=$(echo "$FILES" | wc -l)
INDEX=0
EXPORTS=()
for FILE in $FILES; do
    ((INDEX++))
    if echo "$FILE" | grep -q -E '.pbjson.|.pbenum.'; then
        echo "($INDEX/$COUNT) excluded: $FILE"
        continue
    fi
    echo "($INDEX/$COUNT) included: $FILE"
    SHOW=$(echo "$FILE" | sed "s/\//\n/g" | grep "\.dart" | sed "s/\./\n/g" | head -1 | sed -r 's/(^|_)([a-z])/\U\2/g')
    EXPORTS+=("\nexport '$FILE' show $SHOW;")
done
ALL_EXPORTS=$(IFS=; echo "${EXPORTS[*]}")
echo -e "library turms_client_dart;\n$ALL_EXPORTS" > "$BASEDIR/lib/turms_client.dart"
echo "Done"