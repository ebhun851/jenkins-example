def REMOTE_URL = 'https://github.com/rguruju/jenkins-demo.git'
def BRANCH = 'master'

pipelineJob('pipelinedsl-helloService-CD') {
    logRotator(-1, 10)

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url("${REMOTE_URL}")
                    }
                    branch("${BRANCH}")
                    extensions {
                        cleanBeforeCheckout()
                    }
                }
            }
            scriptPath("Jenkinsfile")
        }
    }
}
