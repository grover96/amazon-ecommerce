pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build -x test' 
            }
	stage('Test'){
		sh './gradlew clean test'
	    }
        }
    }
}
