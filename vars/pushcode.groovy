import groovy.json.* 

@NonCPS
pushintorepo(String projectname, String Source_code_repository){
def jsonSlurper = new JsonSlurper() 
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azuredevops/obj.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def objectid = resultJson.value[0].objectId



def call()
{
  sh """
  curl --location --request GET 'https://dev.azure.com/vickysastryvs/${projectname}/_apis/git/repositories/${Source_code_repository}/refs?api-version=5.1' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206eDIyYXpoejRweHBzbmltMjJod295dzJkNG9xdjZtbzJ3czRsemgyNzZpc2trdW5ueXR5YQ==' -o ob.json
  """
  
  
sh """
curl --location --request POST 'https://dev.azure.com/vickysastryvs/d2/_apis/git/repositories/d2/pushes?api-version=5.1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206eDIyYXpoejRweHBzbmltMjJod295dzJkNG9xdjZtbzJ3czRsemgyNzZpc2trdW5ueXR5YQ==' \
--data-raw '{
  "refUpdates": [
    {
      "name": "refs/heads/master",
      "oldObjectId": "ae4963b5ad493e22cbf21f99401f88911388ae28"
    }
  ],
  "commits": [
    {
      "comment": "Added new file.",
      "changes": [
        {
          "changeType": "add",
          "item": {
            "path": "/bbbbbbbb.yml"
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
    mavenOptions: /'-Xmx3072m/'
    javaHomeOption: '\'JDKVersion\''
    jdkVersionOption: \'1.8\'
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
"""
}
