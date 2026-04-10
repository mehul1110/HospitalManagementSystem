Write-Host "1. Registering..."
$registerResp = Invoke-RestMethod -Uri http://localhost:8080/api/auth/register -Method Post -ContentType "application/json" -Body '{"name": "Admin User", "email": "admin@gmail.com", "password": "Admin@123"}'
$registerResp | ConvertTo-Json

Write-Host "`n2. Logging In..."
$loginResp = Invoke-RestMethod -Uri http://localhost:8080/api/auth/login -Method Post -ContentType "application/json" -Body '{"email": "admin@gmail.com", "password": "Admin@123"}'
$loginResp | ConvertTo-Json
$token = $loginResp.token

Write-Host "`n3. Creating Patient..."
$header = @{"Authorization" = "Bearer $token"}
$patientResp = Invoke-RestMethod -Uri http://localhost:8080/api/patients -Method Post -ContentType "application/json" -Headers $header -Body '{"name": "Rahul Kumar", "age": 27, "gender": "Male", "disease": "Fever", "address": "Chennai", "phoneNumber": "9876543210"}'
Write-Host $patientResp

Write-Host "`n4. Fetching all patients..."
$getAllResp = Invoke-RestMethod -Uri http://localhost:8080/api/patients -Method Get -Headers $header
$getAllResp | ConvertTo-Json
