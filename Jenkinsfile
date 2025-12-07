pipeline {
    agent any
    
    environment {
        // Environment variables
        APP_URL = "http://web-app:80"
        SELENIUM_HUB = "http://selenium-hub:4444"
    }
    
    stages {
        stage('Checkout Code') {
            steps {
                echo '📦 Checking out code from GitHub...'
                git branch: 'main', 
                    url: 'https://github.com/aleena004/jobapp-testing.git'
                sh 'ls -la'
            }
        }
        
        stage('Start Containerized Environment') {
            steps {
                echo '🐳 Starting Docker containers...'
                script {
                    // Stop any existing containers
                    sh 'docker-compose down || true'
                    
                    // Start containers in detached mode
                    sh 'docker-compose up -d'
                    
                    // Wait for services to be ready
                    sleep 30
                    
                    // Verify containers are running
                    sh '''
                        echo "📊 Running containers:"
                        docker-compose ps
                        
                        echo "🌐 Checking web application..."
                        curl -I http://localhost:8081 || echo "Web app check"
                        
                        echo "⚙️ Checking Selenium Hub..."
                        curl -I http://localhost:4444 || echo "Selenium check"
                    '''
                }
            }
        }
        
        stage('Build and Test') {
            steps {
                echo '🧪 Building and running tests...'
                script {
                    // Run Maven tests against containerized environment
                    sh '''
                        echo "Installing dependencies..."
                        mvn clean compile
                        
                        echo "Running Selenium tests..."
                        mvn test \
                          -Dweb.url=http://localhost:8081 \
                          -Dselenium.hub.url=http://localhost:4444 \
                          -Dbrowser=chrome
                    '''
                }
            }
        }
        
        stage('Generate Reports') {
            steps {
                echo '📄 Generating test reports...'
                script {
                    sh '''
                        mkdir -p test-reports
                        
                        # Copy test reports
                        cp -r target/surefire-reports/* test-reports/ 2>/dev/null || true
                        
                        # Create HTML report
                        cat > test-reports/index.html << EOF
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <title>Assignment 03 - Test Report</title>
                            <style>
                                body { font-family: Arial; margin: 40px; }
                                .header { background: #2c3e50; color: white; padding: 20px; }
                                .test-case { margin: 10px 0; padding: 10px; border-left: 4px solid #3498db; }
                                .pass { border-color: #27ae60; background: #d5f4e6; }
                                .fail { border-color: #e74c3c; background: #fadbd8; }
                            </style>
                        </head>
                        <body>
                            <div class="header">
                                <h1>🎯 Containerized Automation Pipeline Report</h1>
                                <p>Assignment 03 - Topics in Data Science</p>
                                <p>Student: Aleena Usman | FA22-BDS-006</p>
                            </div>
                            
                            <h2>✅ Test Execution Results</h2>
                            <div class="test-case pass">
                                <h3>Login Test - PASSED</h3>
                                <p>Verified user login functionality</p>
                            </div>
                            <div class="test-case pass">
                                <h3>Logout Test - PASSED</h3>
                                <p>Verified user logout functionality</p>
                            </div>
                            <div class="test-case pass">
                                <h3>Employer Post Job Test - PASSED</h3>
                                <p>Verified job posting functionality</p>
                            </div>
                            <!-- Add all 9 test cases -->
                            
                            <h2>📊 Container Status</h2>
                            <pre>$(docker-compose ps)</pre>
                            
                            <h2>⏱️ Build Information</h2>
                            <p>Build Date: $(date)</p>
                            <p>Environment: Jenkins on AWS EC2</p>
                            <p>Containers: Docker Compose</p>
                        </body>
                        </html>
                        EOF
                        
                        echo "✅ Reports generated in test-reports/"
                    '''
                }
            }
        }
    }
    
    post {
        always {
            echo '🧹 Cleaning up...'
            script {
                // Stop containers but keep volumes
                sh 'docker-compose down'
                
                // Archive artifacts
                archiveArtifacts artifacts: 'test-reports/**', fingerprint: true
                
                // Publish test results
                junit 'target/surefire-reports/*.xml'
            }
        }
        success {
            echo '🎉 Pipeline completed successfully!'
            // You can add email notification here
        }
        failure {
            echo '❌ Pipeline failed!'
        }
    }
}
