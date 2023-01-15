pipeline {
    agent any
    tools {
        maven 'Maven-3.8.7'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
            post {
                success {
                    echo('Archiving the artifacts')
                    archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
                }
            }
        }
        stage('Running Unit Tests') {
            steps {
                echo('Running Unit tests')
                sh 'mvn clean test -Punit-test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage('Running Integration Tests') {
            steps {
                echo('Running Integration tests')
                sh 'mvn clean verify -Parq-wildfly-managed'
            }
            post {
                always {
                    junit 'target/failsafe-reports/*.xml'
                }
            }
        }
        stage('Deploy to wildfly Server') {
            steps {
                echo('Adapter to deploy to test server')
                sshagent(['centos3-in-the-office']) {
                    sh 'scp target/*.war -o StrictHostKeyChecking=no centos3@192.168.100.210:/home/centos3'
                }

            // deploy adapters: [
            //     //https://www.jenkins.io/doc/pipeline/steps/deploy/#deploy-deploy-warear-to-a-container
            //     jboss7(
            //         credentialsId: 'wildfly-credentials',
            //         url: 'http://192.168.100.210:7410/'
            //     )
            // ],
            // contextPath: null,
            // war: '**/.war'
            }
        }
    }
}
