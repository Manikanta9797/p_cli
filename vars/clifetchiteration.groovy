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
  sh "az boards iteration project list --org https://dev.azure.com/${organization} --project ${projectname}"
}
