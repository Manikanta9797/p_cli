/*def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)
String a=jsonObj.environments.environment.deploy.key
//String a=jsonObj.alm.projects.project.name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
env.name = projectName
*/
def call(){
  //sh 'az extension add --name azure-devops' 
  //sh "sudo cat /home/ec2-user/token.txt | az devops login --organization https://dev.azure.com/${organization}/"
  sh "az boards work-item show --id 55 --org https://dev.azure.com/${Organization}"

}
