node{
   stage('pull image from dockerhub'){
       withCredentials([string(credentialsId: 'dockerHubPwd', variable: 'dockerHubPwd')]) {
       sh 'docker login -u maciek1995 -p ${dockerHubPwd}'
       sh 'docker pull maciek1995/library-app'
        }
   }
   
   stage('run or update stack Library'){
       sh 'docker stack deploy --compose-file /root/Library-Spring-Boot-Rest-API/docker-compose.yml library'
   }
   
}
