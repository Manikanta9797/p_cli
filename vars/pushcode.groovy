def call()
{
sh '''
curl --location --request POST 'https://dev.azure.com/vickysastryvs/d2/_apis/git/repositories/d2/pushes?api-version=5.1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206eDIyYXpoejRweHBzbmltMjJod295dzJkNG9xdjZtbzJ3czRsemgyNzZpc2trdW5ueXR5YQ==' \
--data-raw '{
  "refUpdates": [
    {
      "name": "refs/heads/master",
      "oldObjectId": "b55f28e70f98230f0b3b2f3ced09f9dd34557598"
    }
  ],
  "commits": [
    {
      "comment": "Added new file.",
      "changes": [
        {
          "changeType": "add",
          "item": {
            "path": "/bbb.yml"
          },
          "newContent": {
            "content": "
trigger:
- master

pool:
  vmImage: /'vj'/

steps:
- task: SonarQubePrepare@4
  inputs:
    SonarQube: 'sonar1'
    scannerMode: '/''Other'/''
- task: Maven@3
  inputs:
    mavenPomFile: '/''pom.xml''/'
    mavenOptions: '\''-Xmx3072m'\''
    javaHomeOption: '\''JDKVersion'\''
    jdkVersionOption: '\''1.8'\''
    jdkArchitectureOption: '\''x64'\''
    publishJUnitResults: true
    testResultsFiles: '\''**/surefire-reports/TEST-*.xml'\''
    goals: '\''package'\''
",
            "contentType": "rawText"
          }
        }
      ]
    }
  ]
}'
'''
}
