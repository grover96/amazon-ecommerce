pipeline {
    agent any

    environment {
        USER_NAME = 'rgrover@solstice.com'
        PASSWORD = 'Jaguar123!'
        ORG = 'solstice-org'
        SPACE = 'rgrover-cnt'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building accounts application...'
                sh './gradlew applications/accounts:clean build'
            }
	    }
	    stage('Test'){
	        steps {
	            echo 'Testing accounts application...'
		        sh './gradlew applications/accounts:clean test'
	        }
	    }
	    stage('Deploy'){
	        steps {
	            sh 'cf login -u ${USER_NAME} -p ${PASSWORD} -o ${ORG} -s ${SPACE}'
	            echo 'Deploying accounts application...'
                sh 'cf push accounts --random-route -p applications/accounts/build/libs/applications/accounts-0.0.1-SNAPSHOT.jar'

	        }
	    }
    }
}
