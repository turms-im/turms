#!/bin/bash

set -e -o pipefail

CDPATH=""

SCRIPT="$0"

while [ -h "$SCRIPT" ] ; do
  ls=`ls -ld "$SCRIPT"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    SCRIPT="$link"
  else
    SCRIPT=`dirname "$SCRIPT"`/"$link"
  fi
done

TURMS_HOME=`dirname "$SCRIPT"`

TURMS_HOME=`cd "$TURMS_HOME"; pwd`

while [ "`basename "$TURMS_HOME"`" != "bin" ]; do
  TURMS_HOME=`dirname "$TURMS_HOME"`
done
TURMS_HOME=`dirname "$TURMS_HOME"`

TURMS_CLASSPATH="$TURMS_HOME/lib/*"

if [ ! -z "$JAVA_HOME" ]; then
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

export HOSTNAME=$HOSTNAME

if [ -z "$TURMS_PATH_CONF" ]; then TURMS_PATH_CONF="$TURMS_HOME"/config; fi

if [ -z "$TURMS_PATH_CONF" ]; then
  echo "TURMS_PATH_CONF must be set to the configuration path"
  exit 1
fi

TURMS_PATH_CONF=`cd "$TURMS_PATH_CONF"; pwd`
TURMS_APP_CONF="$TURMS_PATH_CONF"/application.yaml

cd "$TURMS_HOME"

TURMS_JVM_OPTIONS="$TURMS_PATH_CONF"/jvm.options
JVM_OPTIONS=`"$JAVA" -cp "$TURMS_CLASSPATH" org.elasticsearch.tools.launchers.JvmOptionsParser "$TURMS_JVM_OPTIONS"`
TURMS_JAVA_OPTS="$JVM_OPTIONS -Dspring.config.location=$TURMS_APP_CONF"

set -x
if ! echo $* | grep -E '(^-d |-d$| -d |--daemonize$|--daemonize )' > /dev/null; then
  exec \
    "$JAVA" \
    $TURMS_JAVA_OPTS \
    -cp "$TURMS_CLASSPATH" \
    org.springframework.boot.loader.JarLauncher \
    "$@"
  set +x
else
  exec \
    "$JAVA" \
    $TURMS_JAVA_OPTS \
    -cp "$TURMS_CLASSPATH" \
    org.springframework.boot.loader.JarLauncher \
    "$@" \
    <&- &
  set +x
  retval=$?
  pid=$!
  [ $retval -eq 0 ] || exit $retval
  if ! ps -p $pid > /dev/null ; then
    exit 1
  fi
  exit 0
fi

exit $?
