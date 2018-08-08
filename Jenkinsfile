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
                echo 'building orders application...'
                sh './gradlew applications/orders:build -x test'
                echo 'building products application...'
                sh './gradlew applications/products:build -x test'
                echo 'building shipments application...'
                sh './gradlew applications/shipments:build -x test'
            }
	    }
	    stage('Test'){
	        steps {
	            echo 'testing accounts application...'
		        sh './gradlew applications/accounts:test'
		        echo 'testing orders application...'
                sh './gradlew applications/orders:test'
                echo 'testing products application...'
                sh './gradlew applications/products:test'
                echo 'testing shipments application...'
                sh './gradlew applications/shipments:test'
	        }
	    }
	    stage('Deploy'){
	        steps {
	        	echo 'deploying accounts application...'
	            sh 'cf login -u ${USER_NAME} -p ${PASSWORD} -o ${ORG} -s ${SPACE}'
                sh 'cf push accounts --random-route -p applications/accounts/build/libs/applications/accounts-0.0.1-SNAPSHOT.jar'
                sh 'cf push orders --random-route -p applications/orders/build/libs/applications/orders-0.0.1-SNAPSHOT.jar'
                sh 'cf push products --random-route -p applications/products/build/libs/applications/products-0.0.1-SNAPSHOT.jar'
                sh 'cf push shipments --random-route -p applications/shipments/build/libs/applications/shipments-0.0.1-SNAPSHOT.jar'
	        }
	    }
    }
}
