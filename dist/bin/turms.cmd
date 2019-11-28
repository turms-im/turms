@echo off
setlocal enabledelayedexpansion
setlocal enableextensions

set SCRIPT=%0
for %%I in (%SCRIPT%) do set TURMS_HOME=%%~dpI

:turms_home_loop
for %%I in ("%TURMS_HOME:~1,-1%") do set DIRNAME=%%~nxI
if not "%DIRNAME%" == "bin" (
  for %%I in ("%TURMS_HOME%..") do set TURMS_HOME=%%~dpfI
  goto turms_home_loop
)
for %%I in ("%TURMS_HOME%..") do set TURMS_HOME=%%~dpfI

set TURMS_CLASSPATH=!TURMS_HOME!\lib\*
set TURMS_PATH_CONF=!TURMS_HOME!\config
set TURMS_APP_CONF=%TURMS_PATH_CONF%\application.yaml

if defined JAVA_HOME (
  set JAVA="%JAVA_HOME%\bin\java.exe"
) else (
  set JAVA="%TURMS_HOME%\jdk\bin\java.exe"
  set JAVA_HOME="%TURMS_HOME%\jdk"
)

if not exist %JAVA% (
  echo "could not find java in JAVA_HOME or bundled at %TURMS_HOME%\jdk" >&2
  exit /b 1
)
echo !!!!!!!!!!!!!!!!!!!!!!!!!
echo %TURMS_PATH_CONF%
set TURMS_JVM_OPTIONS=%TURMS_PATH_CONF%\jvm.options

@setlocal
for /F "usebackq delims=" %%a in (`CALL %JAVA% -cp "!TURMS_CLASSPATH!" "org.elasticsearch.tools.launchers.JvmOptionsParser" "!TURMS_JVM_OPTIONS!" ^|^| echo jvm_options_parser_failed`) do set JVM_OPTIONS=%%a
@endlocal & set "MAYBE_JVM_OPTIONS_PARSER_FAILED=%JVM_OPTIONS%" & set TURMS_JAVA_OPTS=%JVM_OPTIONS% -Dspring.config.location=%TURMS_APP_CONF%

@echo on
%JAVA% %TURMS_JAVA_OPTS% -cp "%TURMS_CLASSPATH%" "org.springframework.boot.loader.JarLauncher"
@echo off

endlocal
endlocal
exit /b 0