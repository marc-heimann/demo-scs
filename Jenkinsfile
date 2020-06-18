podTemplate(
	containers: [
		containerTemplate(name: 'maven', image: 'maven:3.6-jdk-13', ttyEnabled: true, command: 'cat'),
		containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true)
	],
	volumes: [
		hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
		secretVolume(secretName: 'maven-settings', mountPath: '/root/.m2'),
		persistentVolumeClaim(claimName: 'maven-repository', mountPath: '/root/.m2/repository')
	]
	) {
	node(POD_LABEL) {
	
		stage('Initialize'){
			env.repositoryName = "vi"
			env.deliverableName = "demo-scs"
			env.containerRegistry = "https://vicentral.azurecr.io"
			env.dockerHome = "/var/run/docker.sock"
			env.dockerDaemonURL = "tcp://10.49.145.110:2375"
			env.PATH = "${dockerHome}/bin:${env.PATH}"
		}
	
		stage('Checkout') {    
			checkout scm
		}
	
		stage('Extract maven version from pom') {
	      	env.pom = readMavenPom file: 'pom.xml'      	
	      	final extVer = env.pom.substring(env.pom.lastIndexOf(':') +1, env.pom.length())  
	        env.pomVersion = extVer
	        env.BUILD_ID = env.pomVersion
	    } 
	        
	    stage('Build Java') {	    	
	    	container('maven') {	    
			    sh "mvn clean package -Dmaven.test.skip=true -Pbuild"
			}
	    }
	    
	    /*
	    stage('Execute Unit Tests & SonarQube') {
	    	container('maven') {		    
			    sh "mvn clean test sonar:sonar -Dsonar.projectKey=SCSDEMO -Dsonar.host.url=http://sonarqube-sonarqube:9000 -Dsonar.login=1ab7d99728fd6cb3c77444a32e3785d147208e2d -Pbuild"		    		
			}
	    }
	    */   
	    	    
	    stage('Build & Publish Documentation') {
	    	container('maven') {		    
			    sh "mvn clean verify -Pdocumentation"
			} 
	    }
	    
	    
	    stage('Deploy Java Artifact') {
	    	container('maven') {		    
			    sh "mvn deploy helm:package helm:deploy -s mvn-settings.xml -Dmaven.test.skip=true -Pbuild -X -e"
			
			}
	    }
	    
		stage('Docker Build') {	   
			container('docker') {
				sh "docker build -t ${env.repositoryName}/${env.deliverableName}:${env.pomVersion} ."
			}
		}
	 
	 	stage('Docker Tag Nightly') {	   
			container('docker') {
				sh "docker tag ${env.repositoryName}/${env.deliverableName}:${env.pomVersion} ${env.containerRegistry}/${env.repositoryName}/${env.deliverableName}:${env.pomVersion}.${env.BUILD_NUMBER}"
				sh "docker tag ${env.repositoryName}/${env.deliverableName}:${env.pomVersion} ${env.containerRegistry}/${env.repositoryName}/${env.deliverableName}:latest"
			}
		}
		
		stage('Docker Push Nightly') {
	      withCredentials([usernamePassword(credentialsId: 'vicentral-docker', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
	      	container('docker') {
		        sh "docker login -u ${USERNAME} -p ${PASSWORD} http://${containerRegistry}"
		        sh "docker push ${env.containerRegistry}/${env.repositoryName}/${env.deliverableName}:${env.pomVersion}.${env.BUILD_NUMBER}"
		        sh "docker push ${env.containerRegistry}/${env.repositoryName}/${env.deliverableName}:latest"		        
	        }
	      }
	}
	}
}