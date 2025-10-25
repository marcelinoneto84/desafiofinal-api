@echo off
echo Instalando Gradle 6.9.4...

:: Criar diretório
if not exist "C:\gradle" mkdir "C:\gradle"
cd /d "C:\gradle"

:: Baixar Gradle
echo Baixando Gradle...
curl -L -o gradle-6.9.4-bin.zip https://services.gradle.org/distributions/gradle-6.9.4-bin.zip

:: Extrair
echo Extraindo...
powershell -command "Expand-Archive gradle-6.9.4-bin.zip -DestinationPath ."

:: Limpar
del gradle-6.9.4-bin.zip

echo.
echo ================================
echo GRADLE INSTALADO COM SUCESSO!
echo ================================
echo.
echo PRÓXIMOS PASSOS:
echo 1. Adicione às variáveis de ambiente:
echo    GRADLE_HOME = C:\gradle\gradle-6.9.4
echo    PATH += %%GRADLE_HOME%%\bin
echo.
echo 2. Reinicie o STS
echo.
echo 3. Teste: gradle --version
echo ================================

pause