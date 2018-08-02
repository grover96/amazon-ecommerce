pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew -Dskip.tests clean build' 
            }
        }
    }
}
