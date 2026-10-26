@echo off
setlocal
cd /d "%~dp0"
powershell.exe -NoLogo -NoProfile -Command "& { try { $java = if ($env:JAVA_HOME) { Join-Path $env:JAVA_HOME 'bin\java.exe' } else { (Get-Command java -ErrorAction Stop).Source }; if (-not (Test-Path 'app/campus-supermarket.jar')) { throw 'Missing app/campus-supermarket.jar. Use the Spring Boot distribution ZIP, or build the project first.' }; if ($null -eq $env:DB_PASSWORD) { $secret = Read-Host 'MySQL password (hidden)' -AsSecureString; $env:DB_PASSWORD = [System.Net.NetworkCredential]::new('', $secret).Password; if ($null -eq $env:DB_PASSWORD) { $env:DB_PASSWORD = '' } }; Write-Host 'After startup, open http://localhost:8080/webapp/'; & $java '-jar' 'app/campus-supermarket.jar'; exit $LASTEXITCODE } catch { Write-Host $_.Exception.Message; exit 1 } }"
pause
