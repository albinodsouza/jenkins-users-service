pipeline{
    agent any
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
                sh 'docker build -t user-service:latest .'
}
}
}
}