def call(Map pipelineParams) {
  def url = "";
  def branch = "";
  def sonarBranch = "";
  def sonarEnv = "";
  def sonarScm = "";
  def dockerArgs = "";

  if(pipelineParams.dockerArgs!=null)
  	dockerArgs = pipelineParams.dockerArgs;
  
  if(pipelineParams.dockerImageType!=null)
  	dockerImageType = pipelineParams.dockerImageType;
  else
    dockerImageType = "jenkinsci/jnlp-slave";

  pipeline{
    agent{ label "dev" }

    options {
      buildDiscarder(logRotator(artifactNumToKeepStr: '1',numToKeepStr: '20'))
      timestamps()
    }
    triggers{
      pollSCM(pipelineParams.trigger)
    }
    stages{
      stage('Checkout'){
        steps{
          git branch: pipelineParams.branch, url: pipelineParams.url
        }
      }
      stage('Build'){
      	agent{
      	  docker {
             tool name: 'NodeJS-10', type: 'nodejs'
      	     reuseNode true
             image dockerImageType+":"+pipelineParams.dockerImage
		     args "--name='$BUILD_TAG-Build' -e TZ=Europe/Rome $dockerArgs"
      	  }
    	}
      }
    }
    post {
      // always means, well, always run.
      always {
        cleanWs()
      }
    }
  }
}