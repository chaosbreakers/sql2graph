@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\conf;"%REPO%"\neo4j-tinkerpop-api-impl-0.3-2.3.2.jar;"%REPO%"\neo4j-tinkerpop-api-0.1.jar;"%REPO%"\neo4j-enterprise-2.3.2.jar;"%REPO%"\neo4j-advanced-2.3.2.jar;"%REPO%"\neo4j-management-2.3.2.jar;"%REPO%"\neo4j-query-logging-2.3.2.jar;"%REPO%"\neo4j-com-2.3.2.jar;"%REPO%"\neo4j-enterprise-kernel-2.3.2.jar;"%REPO%"\netty-3.6.3.Final.jar;"%REPO%"\neo4j-backup-2.3.2.jar;"%REPO%"\neo4j-cluster-2.3.2.jar;"%REPO%"\neo4j-ha-2.3.2.jar;"%REPO%"\neo4j-metrics-2.3.2.jar;"%REPO%"\metrics-core-3.1.2.jar;"%REPO%"\metrics-graphite-3.1.2.jar;"%REPO%"\metrics-ganglia-3.1.2.jar;"%REPO%"\gmetric4j-1.0.7.jar;"%REPO%"\oncrpc-1.0.7.jar;"%REPO%"\neo4j-2.3.2.jar;"%REPO%"\neo4j-kernel-2.3.2.jar;"%REPO%"\neo4j-csv-2.3.2.jar;"%REPO%"\neo4j-logging-2.3.2.jar;"%REPO%"\neo4j-lucene-index-2.3.2.jar;"%REPO%"\lucene-core-3.6.2.jar;"%REPO%"\neo4j-graph-algo-2.3.2.jar;"%REPO%"\neo4j-udc-2.3.2.jar;"%REPO%"\neo4j-graph-matching-2.3.2.jar;"%REPO%"\neo4j-cypher-2.3.2.jar;"%REPO%"\scala-library-2.11.7.jar;"%REPO%"\scala-reflect-2.11.7.jar;"%REPO%"\scala-parser-combinators_2.11-1.0.4.jar;"%REPO%"\neo4j-codegen-2.3.2.jar;"%REPO%"\asm-5.0.2.jar;"%REPO%"\neo4j-cypher-compiler-1.9_2.11-2.0.5.jar;"%REPO%"\neo4j-cypher-compiler-2.2_2.11-2.2.6.jar;"%REPO%"\neo4j-cypher-compiler-2.3-2.3.2.jar;"%REPO%"\neo4j-cypher-frontend-2.3-2.3.2.jar;"%REPO%"\parboiled-scala_2.11-1.1.7.jar;"%REPO%"\parboiled-core-1.1.7.jar;"%REPO%"\opencsv-2.3.jar;"%REPO%"\concurrentlinkedhashmap-lru-1.4.2.jar;"%REPO%"\neo4j-jmx-2.3.2.jar;"%REPO%"\neo4j-consistency-check-2.3.2.jar;"%REPO%"\neo4j-consistency-check-legacy-2.3.2.jar;"%REPO%"\commons-configuration-1.10.jar;"%REPO%"\commons-lang-2.6.jar;"%REPO%"\commons-logging-1.1.1.jar;"%REPO%"\neo4j-gremlin-3.2.4.jar;"%REPO%"\gremlin-groovy-3.2.4.jar;"%REPO%"\ivy-2.3.0.jar;"%REPO%"\groovy-2.4.8-indy.jar;"%REPO%"\groovy-groovysh-2.4.8-indy.jar;"%REPO%"\groovy-2.4.8.jar;"%REPO%"\groovy-console-2.4.8.jar;"%REPO%"\groovy-templates-2.4.8.jar;"%REPO%"\groovy-xml-2.4.8.jar;"%REPO%"\groovy-swing-2.4.8.jar;"%REPO%"\jline-2.12.jar;"%REPO%"\groovy-json-2.4.8-indy.jar;"%REPO%"\groovy-jsr223-2.4.8-indy.jar;"%REPO%"\jBCrypt-jbcrypt-0.4.jar;"%REPO%"\gremlin-core-3.2.4.jar;"%REPO%"\gremlin-shaded-3.2.4.jar;"%REPO%"\snakeyaml-1.15.jar;"%REPO%"\javatuples-1.2.jar;"%REPO%"\hppc-0.7.1.jar;"%REPO%"\jcabi-manifests-1.1.jar;"%REPO%"\jcabi-log-0.14.jar;"%REPO%"\slf4j-api-1.7.21.jar;"%REPO%"\jcl-over-slf4j-1.7.21.jar;"%REPO%"\neo4j-io-2.3.2.jar;"%REPO%"\neo4j-unsafe-2.3.2.jar;"%REPO%"\neo4j-primitive-collections-2.3.2.jar;"%REPO%"\neo4j-function-2.3.2.jar;"%REPO%"\commons-lang3-3.3.2.jar;"%REPO%"\mysql-connector-java-6.0.6.jar;"%REPO%"\junit-4.12.jar;"%REPO%"\hamcrest-core-1.3.jar;"%REPO%"\sql2neo4j-1.0-SNAPSHOT.jar
set EXTRA_JVM_ARGUMENTS=-server -Xmx2G -Xms2G
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="main" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" io.openmg.ordos.etl.sql2neo4j.Main %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
