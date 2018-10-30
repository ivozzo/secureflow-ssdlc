#!groovy
 
// imports
import hudson.scm.SCM
import jenkins.model.Jenkins
import jenkins.plugins.git.GitSCMSource
import org.jenkinsci.plugins.workflow.libs.*
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever
 
// parameters
def globalLibrariesParameters = [
  branch:               "develop",
  credentialId:         "",
  implicit:             true,
  name:                 "Pipelines Repository GitHub",
  repository:           "https://github.com/ivozzo/secureflow-pipelines.git"
]
 
// define global library
GitSCMSource gitSCMSource = new GitSCMSource(
  "global-shared-library",
  globalLibrariesParameters.repository,
  globalLibrariesParameters.credentialId,
  "*",
  "",
  false
)
 
// define retriever
SCMSourceRetriever sCMSourceRetriever = new SCMSourceRetriever(gitSCMSource)
 
// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()
 
// get Jenkins Global Libraries
def globalLibraries = jenkins.getDescriptor("org.jenkinsci.plugins.workflow.libs.GlobalLibraries")
 
// define new library configuration
LibraryConfiguration libraryConfiguration = new LibraryConfiguration(globalLibrariesParameters.name, sCMSourceRetriever)
libraryConfiguration.setDefaultVersion(globalLibrariesParameters.branch)
libraryConfiguration.setImplicit(globalLibrariesParameters.implicit)
 
// set new Jenkins Global Library
globalLibraries.get().setLibraries([libraryConfiguration])
 
// save current Jenkins state to disk
jenkins.save()