@echo off
call mvn clean -f pom.xml
if "%errorlevel%"=="1" (
	echo ================================
	echo  clean error.
	echo ================================
	pause
)else (
	echo ================================
	echo  clean success.
	echo ================================
)
echo pom-sys-service-server.......
call mvn package -f pom-sys-service-server.xml
if "%errorlevel%"=="1" (
	echo ================================
	echo  sys-service error.
	echo ================================
	pause
)else (
	echo ================================
	echo  sys-service success.
	echo ================================
)
echo pom-sys-web-server.........
call mvn package -f pom-sys-web-server.xml
if "%errorlevel%"=="1" (
	echo ================================
	echo  sys-web error.
	echo ================================
	pause
)else (
	echo ================================
	echo  sys-web success.
	echo ================================
)
echo pom-biz-service-server........
call mvn package -f pom-biz-service-server.xml
if "%errorlevel%"=="1" (
	echo ================================
	echo  biz-service error.
	echo ================================
	pause
)else (
	echo ================================
	echo  biz-service success.
	echo ================================
)
echo pom-biz-web-server............
call mvn package -f pom-biz-web-server.xml
if "%errorlevel%"=="1" (
	echo ================================
	echo  biz-web error.
	echo ================================
)else (
	echo ================================
	echo  biz-web success.
	echo ================================
)
pause