node{
    stage('Mvn Package'){
     //sh 'cd ./Library-Spring-Boot'
     sh 'pwd'
     sh "mvn clean package"
   }
   
   stage('Build Docker Image'){
     sh 'docker build -t library:v1 .'
   }
     stage('Push Docker Image'){
     withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
        sh "docker login -u maciek1995 -p ${dockerHubPwd}"
     }
     sh'docker tag library:v1 maciek1995/library-app'
     sh 'docker push maciek1995/library-app'
   }
   stage('Run Container'){
     sh 'docker run -p 8000:8080 -d --name library-app library:v1'
}
}
