pipeline{
    agent any
    
    environment{
       DOCKER_IMG_NAME = 'user-service'
       DOCKER_TMP_CONTAINER_NAME = 'top-user-service-container'
       DOCKER_REPO='albinodsouza'
       }
       
    stages{
        stage('clean'){
            steps{
                sh 'mvn clean'
}
}
stage('compile'){
            steps{
                sh 'mvn compile'
} 
}

/*stage('quality'){
            steps{
              sh 'mvn sonar:sonar'
}
}*/
stage('test'){
            steps{
                sh 'mvn test'
}
}


stage('jar'){
            steps{
                sh 'mvn package -DskipTests=true'
}
}
stage('build'){
            steps{
                sh 'mvn package -DskipTests=true'
}
}

stage('dockersize'){
            steps{
            
            echo 'building the docker image for user-service'
                sh "docker build -t ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest -t ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID} ."
}
}

stage('integration tests'){
            steps{
              echo 'running the top-user-service-container for integration testing...'
                sh 'docker run -dp 7070:8080 --rm --name ${DOCKER_TMP_CONTAINER_NAME} ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest'
                sleep 30
                sh 'curl -i http://localhost:7070/api/users'
}
}

stage('docker publish'){
steps{
withDockerRegistry([credentialsId: 'dockercrets', url:'']){
sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID}"
sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest"
}
}
}
}

post{
   always{
 echo 'stopping and removing the top-user-service-container...'
          sh 'docker stop ${DOCKER_TMP_CONTAINER_NAME}'
          sh "docker rmi ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID}"
}
}
}