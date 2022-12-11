@echo off
if not exist %~dp0config\properties.bat (
	mkdir %~dp0config 1>nul 2>&1
	echo @rem put your properties here. >> %~dp0config\properties.bat
	echo warning: check your settings in %~dp0config\properties.bat before first run.
	exit
)
call %~dp0config\properties.bat
set classpath=%~dp0config;%~dp0target\classes
for %%i in ("%~dp0target\dependency\*.jar") do call :addcp %%i
java dem2k.AppMain %*
goto ende
:addcp
set classpath=%1;%classpath%
:ende
