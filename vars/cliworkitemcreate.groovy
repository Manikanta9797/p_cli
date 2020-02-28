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
  sh "az boards work-item create --title ${Title_of_workitem} --type ${Type_of_workitem} --org https://dev.azure.com/${Organization}/ --project ${projectname} --assigned-to ${User_mail_for_workitem}"

}
