#!/bin/bash

# turms-gateway.sh runs in the foreground with a thin jar of turms-gateway by default.
# To run with the fat jar in background, use "./turms-gateway.sh -f -d"
#
# Distribution Structure:
#
# ├─bin
# │  └─turms-gateway.sh
# ├─config
# │  ├─application.yaml
# │  └─jvm.options
# ├─jdk (optional)
# └─lib (for deps and plugins)
#    ├─turms-gateway.jar
#    └─....jar

set -e -o pipefail

# Find JDK

if [ -n "$JAVA_HOME" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  if [ "$(uname -s)" = "Darwin" ]; then
    JAVA="$TURMS_GATEWAY_HOME/jdk/Contents/Home/bin/java"
  else
    JAVA="$TURMS_GATEWAY_HOME/jdk/bin/java"
  fi
fi

if [ ! -x "$JAVA" ]; then
  echo "could not find java in JAVA_HOME or bundled at $JAVA" >&2
  exit 1
fi

# Find turms-gateway env variables

if [ -z "$TURMS_GATEWAY_HOME" ]; then
  SCRIPT="$0"

  while [ -h "$SCRIPT" ]; do
    ls=$(ls -ld "$SCRIPT")
    link=$(expr "$ls" : '.*-> \(.*\)$')
    if expr "$link" : '/.*' >/dev/null; then
      SCRIPT="$link"
    else
      SCRIPT=$(dirname "$SCRIPT")/"$link"
    fi
  done

  TURMS_GATEWAY_HOME=$(dirname "$SCRIPT")
  TURMS_GATEWAY_HOME=$(
    cd "$TURMS_GATEWAY_HOME"
    pwd
  )

  while [ "$(basename "$TURMS_GATEWAY_HOME")" != "bin" ]; do
    TURMS_GATEWAY_HOME=$(dirname "$TURMS_GATEWAY_HOME")
  done
  TURMS_GATEWAY_HOME=$(dirname "$TURMS_GATEWAY_HOME")
fi

TURMS_GATEWAY_LIBRARY=".:$TURMS_GATEWAY_HOME/lib/*"

if [ -z "$TURMS_GATEWAY_PATH_CONF" ]; then TURMS_GATEWAY_PATH_CONF="$TURMS_GATEWAY_HOME"/config; fi

if [ -z "$TURMS_GATEWAY_PATH_CONF" ]; then
  echo "TURMS_GATEWAY_PATH_CONF must be set to the configuration path"
  exit 1
fi

TURMS_GATEWAY_PATH_CONF=$(
  cd "$TURMS_GATEWAY_PATH_CONF"
  pwd
)
TURMS_GATEWAY_APP_CONF="$TURMS_GATEWAY_PATH_CONF"/application.yaml
TURMS_GATEWAY_JVM_OPTIONS="$TURMS_GATEWAY_PATH_CONF"/jvm.options

cd "$TURMS_GATEWAY_HOME"

# Print env variables
echo "Current environment:"
echo "JAVA_HOME: $JAVA"
echo "TURMS_GATEWAY_HOME: $TURMS_GATEWAY_HOME"
echo "TURMS_GATEWAY_APP_CONF: $TURMS_GATEWAY_APP_CONF"
echo "TURMS_GATEWAY_JVM_OPTIONS: $TURMS_GATEWAY_JVM_OPTIONS"
echo "TURMS_GATEWAY_LIBRARY: $TURMS_GATEWAY_LIBRARY"

# Parse options

USE_FAT_JAR=false
DAEMONIZE=false
for option in "$@"; do
  case "$option" in
  -f | --fat)
    USE_FAT_JAR=true
    ;;
  -d | --daemonize)
    DAEMONIZE=true
    ;;
  esac
done

JVM_OPTIONS="$(tr -d "\r" < "$TURMS_GATEWAY_JVM_OPTIONS" | grep -v "^#" | tr "\n" " " | tr -s " ")"
JVM_OPTIONS+=" -Dspring.config.location=$TURMS_GATEWAY_APP_CONF"

MAIN_CLASS="im.turms.turms.TurmsGatewayApplication"
if [ "$USE_FAT_JAR" = true ]; then
  MAIN_CLASS="org.springframework.boot.loader.JarLauncher"
fi

# Run
if [ "$DAEMONIZE" = true ]; then
  set -x
  exec \
    "$JAVA" \
    $JVM_OPTIONS \
    -cp "$TURMS_GATEWAY_LIBRARY" \
    "$MAIN_CLASS" \
    "$@" \
    <&- &
  set +x
else
  set -x
  exec \
    "$JAVA" \
    $JVM_OPTIONS \
    -cp "$TURMS_GATEWAY_LIBRARY" \
    "$MAIN_CLASS" \
    "$@"
  set +x
  retval=$?
  pid=$!
  [ $retval -eq 0 ] || exit $retval
  if ! ps -p $pid >/dev/null; then
    exit 1
  fi
  exit 0
fi

exit $?
