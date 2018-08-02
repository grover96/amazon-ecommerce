pipeline {
    agent any

    environment {
        USER_NAME = credentials('CFUSER')
        PASSWORD = credentials('CFPASS')
        ORG = 'solstice-org'
        SPACE = 'rgrover-cnt'
    }

    stages {
        stage('Build') {
            steps {
                echo 'building accounts application...'
                sh './gradlew applications/accounts:build -x test'
                sh './gradlew applications/accounts:bootRun
            }
	    }
	    stage('Test'){
	        steps {
	            echo 'testing accounts application...'
		        sh './gradlew applications/accounts:test'
	        }
	    }
	    stage('Deploy'){
	        steps {
	        	echo 'deploying accounts application...'
	            sh 'cf login -u ${USER_NAME} -p ${PASSWORD} -o ${ORG} -s ${SPACE}'
                sh 'cf push accounts --random-route -p applications/accounts/build/libs/applications/accounts-0.0.1-SNAPSHOT.jar'

	        }
	    }
    }
}
