node{
   stage('pull image from dockerhub'){
       checkout scm
       withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
       sh "docker login -u maciek1995 -p ${dockerHubPwd}"
       sh 'docker pull "maciek1995/library-app'
        }
   }
   
   stage('run or update stack Library'){
       sh 'docker service update library --image [maciek1995/library-app] --force'
       
   }
   
}

