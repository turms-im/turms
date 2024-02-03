#!/bin/bash

BASEDIR=$(pwd)
if [ "$(basename "$BASEDIR")" == "tools" ]; then
    BASEDIR="$(dirname "$BASEDIR")"
fi

echo "Base directory: $BASEDIR"

# turms-client-cpp
echo "Generating proto files for turms-client-cpp..."
cd "$BASEDIR/turms-client-cpp" || exit 10
if ! sh generate_proto.sh; then
    echo "Error when generating proto files for turms-client-cpp"
    exit 11
fi
echo "Generated proto files for turms-client-cpp"

# turms-client-dart
echo "Generating proto files for turms-client-dart..."
cd "$BASEDIR/turms-client-dart" || exit 20
if ! sh tool/generate_proto.sh; then
    echo "Error when generating proto files for turms-client-dart"
    exit 21
fi
echo "Generated proto files for turms-client-dart"
dart format .

# turms-client-js
echo "Generating proto files for turms-client-js..."
cd "$BASEDIR/turms-client-js" || exit 30
if ! npm run protoc:compile; then
    echo "Error when generating proto files for turms-client-js"
    exit 31
fi
npm run lint:fix;
echo "Generated proto files for turms-client-js"

# turms-client-kotlin
echo "Generating proto files for turms-client-kotlin..."
cd "$BASEDIR/turms-client-kotlin" || exit 40
if ! sh generate_proto.sh; then
    echo "Error when generating proto files for turms-client-kotlin"
    exit 41
fi
mvn antrun:run@ktlint-format -P lint
mvn com.diffplug.spotless:spotless-maven-plugin:apply
echo "Generated proto files for turms-client-kotlin"

# turms-client-swift
echo "Generating proto files for turms-client-swift..."
cd "$BASEDIR/turms-client-swift" || exit 50
if ! sh generate_proto.sh; then
    echo "Error when generating proto files for turms-client-swift"
    exit 51
fi
echo "Generated proto files for turms-client-swift"

echo "Done"