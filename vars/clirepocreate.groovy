def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)
String a=jsonObj.environments.environment.deploy.Organization
String Organization=a.replaceAll("\\[", "").replaceAll("\\]","");
String b=jsonObj.environments.environment.deploy.projectname
String projectname=b.replaceAll("\\[", "").replaceAll("\\]","");
String c=jsonObj.environments.environment.deploy.New_Repository_Name
String New_Repository_Name=c.replaceAll("\\[", "").replaceAll("\\]","");
//String a=jsonObj.alm.projects.project.name
//String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
//env.name = projectName


  //sh 'az extension add --name azure-devops' 
  //sh 'sudo cat /home/ec2-user/token.txt | az devops login --organization https://dev.azure.com/vickysastryvs/'
  sh "sudo cat /home/ec2-user/token.txt | az devops login --organization https://dev.azure.com/${Organization}/"
  sh "az repos create --name ${New_Repository_Name} --organization https://dev.azure.com/${Organization} --project ${projectname}"

}
