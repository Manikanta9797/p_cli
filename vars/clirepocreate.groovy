/*import groovy.json.* 

@NonCPS
pushintorepo(String projectname, String Source_code_repository){
def jsonSlurper = new JsonSlurper() 
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azuredevops/obj.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def objectid = resultJson.value[0].objectId
//println(objectid)
def filename= "pipe.yml"  
def chbranch = "master"
def pomname = "pom.xml"
def goa = "package"
String ppp= "ubuntu-latest"
    
    
 
  sh """
   curl --location --request POST 'https://dev.azure.com/vickysastryvs/${projectname}/_apis/git/repositories/${Source_code_repository}/pushes?api-version=5.1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206eDIyYXpoejRweHBzbmltMjJod295dzJkNG9xdjZtbzJ3czRsemgyNzZpc2trdW5ueXR5YQ==' \
--data-raw '{
  "refUpdates": [
    {
      "name": "refs/heads/master",
      "oldObjectId": "${objectid}"
    }
  ],
  "commits": [
    {
      "comment": "Added new file.",
      "changes": [
        {
          "changeType": "add",
          "item": {
            "path": "/${filename}"
          },
          "newContent": {
            "content": "
trigger:
- master

pool:
  vmImage: '\''${ppp}'\''
  
steps:
- task: SonarQubePrepare@4
  inputs:
    SonarQube: '\''sonar1'\''
    scannerMode: '\''Other'\''
- task: Maven@3
  inputs:
    mavenPomFile: '\''pom.xml'\''
    mavenOptions: '\''-Xmx3072m'\''
    javaHomeOption: '\''JDKVersion'\''
    jdkVersionOption: '\''1.8'\''
    jdkArchitectureOption: '\''x64'\''
    publishJUnitResults: true   
    goals: '\''package'\''
",
            "contentType": "rawtext"
          }
        }
      ]
    }
  ]
}'
  """
  

} 
*/
def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
//println(jsonObj.environments.environment)
String a = jsonObj.environments.environment.deploy.Organization
String Organization = a.replaceAll("\\[", "").replaceAll("\\]","");
String b = jsonObj.environments.environment.deploy.projectname
String projectname = b.replaceAll("\\[", "").replaceAll("\\]","");
String c = jsonObj.environments.environment.deploy.New_Repository_Name
String New_Repository_Name = c.replaceAll("\\[", "").replaceAll("\\]","");

String d = jsonObj.environments.environment.deploy.Title_of_workitem
String Title_of_workitem = d.replaceAll("\\[", "").replaceAll("\\]","");
String e = jsonObj.environments.environment.deploy.Type_of_workitem
String Type_of_workitem = e.replaceAll("\\[", "").replaceAll("\\]","");
String f = jsonObj.environments.environment.deploy.User_mail_for_workitem
String User_mail_for_workitem = f.replaceAll("\\[", "").replaceAll("\\]","");
String g = jsonObj.environments.environment.deploy.Pipeline_Name
String Pipeline_Name = g.replaceAll("\\[", "").replaceAll("\\]","");
String h = jsonObj.environments.environment.deploy.Source_code_repository
String Source_code_repository = h.replaceAll("\\[", "").replaceAll("\\]","");
String i = jsonObj.environments.environment.deploy.Branch
String Branch = i.replaceAll("\\[", "").replaceAll("\\]","");
  
  def choosebranch = "master"
  def filename = "pom.xml"
  def goals = "package"
  sh "sudo cp /home/ec2-user/first.yml /var/lib/jenkins/workspace/azuredevops/"  
  sh "sudo chown jenkins first.yml"
  def file = new File('/var/lib/jenkins/workspace/azuredevops/first.yml');
  sh "cat first.yml"
  def newConfig = file.text.replace('$(brnch)', choosebranch).replace('$(pom)', filename).replace('$(goal)', goals)
  file.text = newConfig
  sh "cat first.yml"
  sh "sudo sh /home/ec2-user/add.sh"
  
  
    
  
  //fetch branches to store objectid
 /* sh """
  curl --location --request GET 'https://dev.azure.com/vickysastryvs/${projectname}/_apis/git/repositories/${Source_code_repository}/refs?api-version=5.1' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206eDIyYXpoejRweHBzbmltMjJod295dzJkNG9xdjZtbzJ3czRsemgyNzZpc2trdW5ueXR5YQ==' -o obj.json
  """
    */
 // pushintorepo(projectname,Source_code_repository)  
 
//String a=jsonObj.alm.projects.project.name
//String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");


  //sh 'az extension add --name azure-devops' 
  sh 'sudo cat /home/ec2-user/token.txt | az devops login --organization https://dev.azure.com/vickysastryvs/'
 // sh "sudo cat /home/ec2-user/token.txt | az devops login --organization https://dev.azure.com/${Organization}/" //login
  //create a repo
  //sh "az repos create --name ${New_Repository_Name} --organization https://dev.azure.com/${Organization} --project ${projectname}" 
 
  
  
  // create workitem
  //sh "az boards work-item create --title ${Title_of_workitem} --type ${Type_of_workitem} --org https://dev.azure.com/${Organization}/ --project ${projectname} --assigned-to ${User_mail_for_workitem}"
  // create pipeline
  //def path = "pipe.yml"
  sh "az pipelines create --name ${Pipeline_Name} --repository ${Source_code_repository} --branch ${Branch} --repository-type tfsgit --yml-path first.yml --org https://dev.azure.com/${Organization} --project ${projectname}"
  // fetch repos
  /*sh "az repos list --org https://dev.azure.com/${Organization} -p ${projectname}"
  //fetch iterations
  sh "az boards iteration project list --org https://dev.azure.com/${Organization} --project ${projectname}"
  // list pipelines
  sh "az pipelines list --org https://dev.azure.com/${Organization} -p ${projectname}"  */
}



