#!/bin/bash

# run.sh runs in the foreground with a thin jar of turms by default.
# To run with the fat jar in background, use "./run.sh -f -d"
#
# Home Directory Structure:
#
# ├─bin
# │  └─run.sh
# ├─config
# │  ├─application.yaml
# │  └─jvm.options
# ├─hprof (generated)
# ├─jdk (optional)
# │─jfr (generated)
# │─lib (for deps and plugins)
# │  ├─turms.jar
# │  └─....jar
# └─log (generated)

set -e -o pipefail

# Find JDK

if [ -n "$JAVA_HOME" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  if [ "$(uname -s)" = "Darwin" ]; then
    JAVA="$TURMS_HOME/jdk/Contents/Home/bin/java"
  else
    JAVA="$TURMS_HOME/jdk/bin/java"
  fi
fi

if [ ! -x "$JAVA" ]; then
  echo "could not find java in JAVA_HOME or bundled at $JAVA" >&2
  exit 1
fi

# Find turms env variables

if [ -z "$TURMS_HOME" ]; then
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

  TURMS_HOME=$(dirname "$SCRIPT")
  TURMS_HOME=$(
    cd "$TURMS_HOME"
    pwd
  )

  while [ "$(basename "$TURMS_HOME")" != "bin" ]; do
    TURMS_HOME=$(dirname "$TURMS_HOME")
  done
  TURMS_HOME=$(dirname "$TURMS_HOME")
fi

TURMS_LIB=".:$TURMS_HOME/lib/*"

if [ -z "$TURMS_PATH_CONF" ]; then TURMS_PATH_CONF="$TURMS_HOME"/config; fi

if [ -z "$TURMS_PATH_CONF" ]; then
  echo "TURMS_PATH_CONF must be set to the configuration path"
  exit 1
fi

TURMS_PATH_CONF=$(
  cd "$TURMS_PATH_CONF"
  pwd
)
if [ -z "$TURMS_APP_CONF" ]; then
  TURMS_APP_CONF="$TURMS_PATH_CONF"/application.yaml
fi
if [ -z "$TURMS_JVM_CONF" ]; then
  TURMS_JVM_CONF="$TURMS_PATH_CONF"/jvm.options
fi

cd "$TURMS_HOME"

# Print env variables
echo "Current environment:"
echo "JAVA_HOME: $JAVA"
echo "TURMS_HOME: $TURMS_HOME"
echo "TURMS_APP_CONF: $TURMS_APP_CONF"
echo "TURMS_JVM_CONF: $TURMS_JVM_CONF"
echo "TURMS_JVM_OPTS: $TURMS_JVM_OPTS"
echo "TURMS_LIB: $TURMS_LIB"

# Parse options

DAEMONIZE=false
USE_FAT_JAR=false
for option in "$@"; do
  case "$option" in
  -d | --daemonize)
    DAEMONIZE=true
    ;;
  -f | --fat)
    USE_FAT_JAR=true
    ;;
  esac
done

# Filter -> Replace CRLF with whitespace -> Trim redundant whitespaces -> Expand vars
JVM_OPTIONS="$(
  tr -d "\r" <"${TURMS_JVM_CONF}" |
    grep -v "^#" |
    tr "\n" " " |
    tr -s " " |
    sed "s@\${TURMS_HOME}@${TURMS_HOME}@g"
)"
JVM_OPTIONS+=" -Dspring.config.location=classpath:/,$TURMS_APP_CONF"
JVM_OPTIONS+=" ${TURMS_JVM_OPTS}"

MAIN_CLASS="im.turms.turms.TurmsApplication"
if [ "$USE_FAT_JAR" = true ]; then
  MAIN_CLASS="org.springframework.boot.loader.JarLauncher"
fi

# Create necessary dirs
[ ! -d "${TURMS_HOME}/hprof" ] && mkdir "${TURMS_HOME}/hprof"
[ ! -d "${TURMS_HOME}/jfr" ] && mkdir "${TURMS_HOME}/jfr"
[ ! -d "${TURMS_HOME}/log" ] && mkdir "${TURMS_HOME}/log"

# Run
if [ "$DAEMONIZE" = true ]; then
  set -x
  exec \
    "$JAVA" \
    $JVM_OPTIONS \
    -cp "$TURMS_LIB" \
    "$MAIN_CLASS" \
    "$@" \
    <&- &
  set +x
else
  set -x
  exec \
    "$JAVA" \
    $JVM_OPTIONS \
    -cp "$TURMS_LIB" \
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
