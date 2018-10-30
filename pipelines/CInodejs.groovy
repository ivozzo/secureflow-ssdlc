def call(Map pipelineParams) {
  def url = "";
  def branch = "";
  def sonarBranch = "";
  def sonarEnv = "";
  def sonarScm = "";
  def dockerArgs = "";

  pipeline{
    stages{
      stage('Checkout'){
        steps{
          git branch: pipelineParams.branch, url: pipelineParams.url
        }
      }
      stage('Build'){
      	agent{
      	  docker {
            image 'node:10-alpine'
		        args "--name='$BUILD_TAG-Build' -e TZ=Europe/Rome $dockerArgs"
      	  }
    	  }
        steps {
          sh 'node --version'
        }
      }
    }
    post {
      always {
        cleanWs()
      }
    }
  }
}